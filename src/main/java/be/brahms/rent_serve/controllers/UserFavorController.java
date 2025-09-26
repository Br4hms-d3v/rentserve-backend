package be.brahms.rent_serve.controllers;

import be.brahms.rent_serve.hateaos.UserFavorAssembler;
import be.brahms.rent_serve.models.dtos.userFavor.UserFavorDto;
import be.brahms.rent_serve.models.entities.UserFavor;
import be.brahms.rent_serve.services.UserFavorService;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user-favor/")
public class UserFavorController {

    private final UserFavorService userFavorService;
    private final UserFavorAssembler userFavorAssembler;

    public UserFavorController(UserFavorService userFavorService, UserFavorAssembler userFavorAssembler) {
        this.userFavorService = userFavorService;
        this.userFavorAssembler = userFavorAssembler;
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

}
