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

/**
 * This controller manages user favor
 * <ul>
 *     <li> Get a list of user favour </li>
 *     <li> Get a list of user favour by favor ID </li>
 *     <li> Get a list of user favour activated </li>
 *     <li> Get a list of user favour deactivated </li>
 *     <li> Get details of the user favor by his ID </li>
 *     <li> Get a list of user favor by grouped user ID</li>
 *     <li> Update a user favor </li>
 *     <li> Delete a user favor by ID </li>
 *     <li> Create a new user favor</li>
 * </ul>
 */
@RestController
@RequestMapping("/api/user-favor/")
public class UserFavorController {

    private final UserFavorService userFavorService;
    private final UserFavorAssembler userFavorAssembler;
    private final UserFavorIdAssembler userFavorIdAssembler;

    /**
     * Constructor to create UserFavorController.
     *
     * @param userFavorService     The service to manage user favor.
     * @param userFavorAssembler   The assembler to make models from user favor.
     * @param userFavorIdAssembler The assembler to make models from user favor id.
     */
    public UserFavorController(UserFavorService userFavorService, UserFavorAssembler userFavorAssembler, UserFavorIdAssembler userFavorIdAssembler) {
        this.userFavorService = userFavorService;
        this.userFavorAssembler = userFavorAssembler;
        this.userFavorIdAssembler = userFavorIdAssembler;
    }

    /**
     * Get all user favor from the database.
     *
     * @return a list of user favor
     */
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

    /**
     * Get all user favor from the database by favor ID.
     *
     * @param favorId The ID of the favor to get a list of user favor
     * @return a list of user favor
     */
    @GetMapping("list/{favorId}")
    @PreAuthorize("hasAnyRole('MEMBER', 'MODERATOR', 'ADMIN')")
    public ResponseEntity<List<EntityModel<UserFavorDto>>> getUserFavourById(@PathVariable long favorId) {
        List<UserFavor> userFavorsById = userFavorService.findAllUserFavourById(favorId); // Get all UserFavor objects by the given favorId
        // Change each UserFavor to UserFavorDto (DTO is a data transfer object)
        List<UserFavorDto> userFavourToDto = userFavorsById
                .stream() // go through the list one by one
                .map(UserFavorDto::fromEntity) // change UserFavor to UserFavorDto
                .toList(); // make a new list

        // Convert each UserFavorDto to an EntityModel (used for adding links in HATEOAS)
        List<EntityModel<UserFavorDto>> listUserFavourDtoToModel = userFavourToDto
                .stream()// go through the list
                .map(userFavorAssembler::toModel) // change to EntityModel
                .toList();// make a new list

        // Return the final list
        return ResponseEntity.ok(listUserFavourDtoToModel);
    }

    /**
     * Get all user favor from the database that is available.
     *
     * @return a list of user favor who the availability is true
     */
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

    /**
     * Get all user favor from the database that is not available.
     *
     * @return a list of user favor who the availability is false
     */
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

    /**
     * Get a details user favor by his ID.
     *
     * @param id the id of the user favor to get
     * @return a details user favor
     */
    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('MEMBER','MODERATOR','ADMIN')")
    public ResponseEntity<EntityModel<UserFavorByIdDto>> getUserFavorById(@PathVariable long id) {
        UserFavor userFavorId = userFavorService.findUserFavorById(id);
        UserFavorByIdDto userFavorIdToDto = UserFavorByIdDto.fromEntity(userFavorId);

        EntityModel<UserFavorByIdDto> userFavorDtoToModel = userFavorIdAssembler.toModel(userFavorIdToDto);
        return ResponseEntity.ok(userFavorDtoToModel);
    }

    /**
     * Get a list of user favor by grouped user ID.
     *
     * @param userId The ID of the user to get user favor
     * @return a list of user favor grouped by favor
     */
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

    /**
     * Update a user favor.
     *
     * @param id   the id of the user favor to update
     * @param form the form to update user favor
     * @return a user favor updated
     */
    @PutMapping("{id}/edit")
    @PreAuthorize("hasAnyRole('MEMBER','MODERATOR','ADMIN')")
    public ResponseEntity<EntityModel<UserFavorDto>> updateUserFavor(@PathVariable long id, @RequestBody @Valid UserFavorForm form) {
        UserFavor editUserFavor = userFavorService.updateUserFavor(id, form.toEntity());
        UserFavorDto userFavorDto = UserFavorDto.fromEntity(editUserFavor);
        EntityModel<UserFavorDto> userFavorDtoToModel = userFavorAssembler.toModel(userFavorDto);

        return ResponseEntity.ok(userFavorDtoToModel);
    }

    /**
     * Delete a user favor by his ID.
     *
     * @param id the id of the user favor to delete
     * @return a success message after delete user favor
     */
    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('MEMBER','MODERATOR','ADMIN')")
    public ResponseEntity<EntityModel<UserFavorDeleteDto>> deleteUserFavor(@PathVariable long id) {
        userFavorService.deleteUserFavor(id);
        UserFavorDeleteDto userFavorDeleteDto = UserFavorDeleteDto.successDeleting();
        EntityModel<UserFavorDeleteDto> userFavorDeleteDtoTOEntityModel = userFavorAssembler.toModel(userFavorDeleteDto);

        return ResponseEntity.ok(userFavorDeleteDtoTOEntityModel);
    }

    /**
     * Create a new user favor.
     *
     * @param form The form to create a new user favor
     * @return a new user favor created
     */
    @PostMapping("new")
    @PreAuthorize("hasAnyRole('MEMBER','MODERATOR','ADMIN')")
    public ResponseEntity<EntityModel<UserFavorDto>> createUserFavor(@RequestBody @Valid UserFavorNewForm form) {
        UserFavor createUserFavor = userFavorService.createUserFavor(form.toEntity());
        UserFavorDto userFavorDto = UserFavorDto.fromEntity(createUserFavor);

        EntityModel<UserFavorDto> userFavorDtoToModel = userFavorAssembler.toModel(userFavorDto);

        return ResponseEntity.ok().body(userFavorDtoToModel);
    }

}
