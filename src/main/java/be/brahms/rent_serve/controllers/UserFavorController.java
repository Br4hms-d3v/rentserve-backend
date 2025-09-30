package be.brahms.rent_serve.controllers;

import be.brahms.rent_serve.hateaos.userFavor.UserFavorAssembler;
import be.brahms.rent_serve.hateaos.userFavor.UserFavorGroupedByUserIdAssembler;
import be.brahms.rent_serve.hateaos.userFavor.UserFavorIdAssembler;
import be.brahms.rent_serve.models.dtos.userFavor.UserFavorByIdDto;
import be.brahms.rent_serve.models.dtos.userFavor.UserFavorDeleteDto;
import be.brahms.rent_serve.models.dtos.userFavor.UserFavorDto;
import be.brahms.rent_serve.models.dtos.userFavor.UserFavourGroupedByFavourDto;
import be.brahms.rent_serve.models.entities.UserFavor;
import be.brahms.rent_serve.models.forms.userFavor.UserFavorForm;
import be.brahms.rent_serve.models.forms.userFavor.UserFavorNewForm;
import be.brahms.rent_serve.services.UserFavorService;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user-favor/")
public class UserFavorController {

    private final UserFavorService userFavorService;
    private final UserFavorAssembler userFavorAssembler;
    private final UserFavorIdAssembler userFavorIdAssembler;

    public UserFavorController(UserFavorService userFavorService, UserFavorAssembler userFavorAssembler, UserFavorIdAssembler userFavorIdAssembler) {
        this.userFavorService = userFavorService;
        this.userFavorAssembler = userFavorAssembler;
        this.userFavorIdAssembler = userFavorIdAssembler;
    }

    @GetMapping("list")
    @PreAuthorize("hasAnyRole('MEMBER', 'MODERATOR', 'ADMIN')")
    public ResponseEntity<List<EntityModel<UserFavorDto>>> getUserFavour() {
        List<UserFavor> userFavourList = userFavorService.findAllUserFavour();
        List<UserFavorDto> userFavourToDto = userFavourList
                .stream()
                .map(UserFavorDto::fromEntity)
                .toList();

        List<EntityModel<UserFavorDto>> listUserFavourDtoToEntity = userFavourToDto
                .stream()
                .map(userFavorAssembler::toModel)
                .toList();

        return ResponseEntity.ok(listUserFavourDtoToEntity);
    }

    @GetMapping("list/{favorId}")
    @PreAuthorize("hasAnyRole('MEMBER', 'MODERATOR', 'ADMIN')")
    public ResponseEntity<List<EntityModel<UserFavorDto>>> getUserFavourById(@PathVariable long favorId) {
        List<UserFavor> userFavorsById = userFavorService.findAllUserFavourById(favorId);
        List<UserFavorDto> userFavourToDto = userFavorsById
                .stream()
                .map(UserFavorDto::fromEntity)
                .toList();

        List<EntityModel<UserFavorDto>> listUserFavourDtoToModel = userFavourToDto
                .stream()
                .map(userFavorAssembler::toModel)
                .toList();


        return ResponseEntity.ok(listUserFavourDtoToModel);
    }

    @GetMapping("activated")
    @PreAuthorize("hasAnyRole('MODERATOR', 'ADMIN')")
    public ResponseEntity<List<EntityModel<UserFavorDto>>> getAllUserFavourAvailable() {
        List<UserFavor> userFavourAvailableList = userFavorService.findAllUserFavourAvailable();
        List<UserFavorDto> listUserFavourToDto = userFavourAvailableList
                .stream()
                .map(UserFavorDto::fromEntity)
                .toList();

        List<EntityModel<UserFavorDto>> userFavourAvailableToModel = listUserFavourToDto
                .stream()
                .map(userFavorAssembler::toModel)
                .toList();

        return ResponseEntity.ok(userFavourAvailableToModel);
    }

    @GetMapping("deactivated")
    @PreAuthorize("hasAnyRole('MODERATOR', 'ADMIN')")
    public ResponseEntity<List<EntityModel<UserFavorDto>>> getAllUserFavourNotAvailable() {
        List<UserFavor> userFavourNotAvailableList = userFavorService.findAllUserFavourNotAvailable();
        List<UserFavorDto> listUserFavourToDto = userFavourNotAvailableList
                .stream()
                .map(UserFavorDto::fromEntity)
                .toList();

        List<EntityModel<UserFavorDto>> userFavourNotAvailableToModel = listUserFavourToDto
                .stream()
                .map(userFavorAssembler::toModel)
                .toList();

        return ResponseEntity.ok(userFavourNotAvailableToModel);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('MEMBER','MODERATOR','ADMIN')")
    public ResponseEntity<EntityModel<UserFavorByIdDto>> getUserFavorById(@PathVariable long id) {
        UserFavor userFavorId = userFavorService.findUserFavorById(id);
        UserFavorByIdDto userFavorIdToDto = UserFavorByIdDto.fromEntity(userFavorId);

        EntityModel<UserFavorByIdDto> userFavorDtoToModel = userFavorIdAssembler.toModel(userFavorIdToDto);
        return ResponseEntity.ok(userFavorDtoToModel);
    }

    @GetMapping("grouped/{userId}")
    @PreAuthorize("hasAnyRole('MEMBER','MODERATOR','ADMIN')")
    public ResponseEntity<List<EntityModel<UserFavourGroupedByFavourDto>>> getUserFavorByUserId(@PathVariable long userId) {
        List<UserFavor> userFavorsFromUser = userFavorService.getUserFavorByUserId(userId);

        Map<Long, List<UserFavor>> grouped = userFavorsFromUser
                .stream()
                .collect(Collectors.groupingBy(uf -> uf.getFavor().getId()));

        List<UserFavourGroupedByFavourDto> listUserFavour = grouped.values()
                .stream()
                .map(UserFavourGroupedByFavourDto::fromGroup)
                .toList();

        List<EntityModel<UserFavourGroupedByFavourDto>> listUserFavourToModel = listUserFavour
                .stream()
                .map(UserFavorGroupedByUserIdAssembler::toModel)
                .toList();

        return ResponseEntity.ok(listUserFavourToModel);
    }

    @PutMapping("{id}/edit")
    @PreAuthorize("hasAnyRole('MEMBER','MODERATOR','ADMIN')")
    public ResponseEntity<EntityModel<UserFavorDto>> updateUserFavor(@PathVariable long id, @RequestBody @Valid UserFavorForm form) {
        UserFavor editUserFavor = userFavorService.updateUserFavor(id, form.toEntity());
        UserFavorDto userFavorDto = UserFavorDto.fromEntity(editUserFavor);
        EntityModel<UserFavorDto> userFavorDtoToModel = userFavorAssembler.toModel(userFavorDto);

        return ResponseEntity.ok(userFavorDtoToModel);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('MEMBER','MODERATOR','ADMIN')")
    public ResponseEntity<EntityModel<UserFavorDeleteDto>> deleteUserFavor(@PathVariable long id) {
        userFavorService.deleteUserFavor(id);
        UserFavorDeleteDto userFavorDeleteDto = UserFavorDeleteDto.successDeleting();
        EntityModel<UserFavorDeleteDto> userFavorDeleteDtoTOEntityModel = userFavorAssembler.toModel(userFavorDeleteDto);

        return ResponseEntity.ok(userFavorDeleteDtoTOEntityModel);
    }

    @PostMapping("new")
    @PreAuthorize("hasAnyRole('MEMBER','MODERATOR','ADMIN')")
    public ResponseEntity<EntityModel<UserFavorDto>> createUserFavor(@RequestBody @Valid UserFavorNewForm form) {
        UserFavor createUserFavor = userFavorService.createUserFavor(form.toEntity());
        UserFavorDto userFavorDto = UserFavorDto.fromEntity(createUserFavor);

        EntityModel<UserFavorDto> userFavorDtoToModel = userFavorAssembler.toModel(userFavorDto);

        return ResponseEntity.ok().body(userFavorDtoToModel);
    }

}
