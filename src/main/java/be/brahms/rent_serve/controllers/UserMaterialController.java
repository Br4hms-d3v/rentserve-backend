package be.brahms.rent_serve.controllers;

import be.brahms.rent_serve.hateaos.UserMaterialAssembler;
import be.brahms.rent_serve.models.dtos.userMaterial.UserMaterialDto;
import be.brahms.rent_serve.models.entities.UserMaterial;
import be.brahms.rent_serve.services.UserMaterialService;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user/material/")
public class UserMaterialController {

    private final UserMaterialService userMaterialService;
    private final UserMaterialAssembler userMaterialAssembler;

    public UserMaterialController(UserMaterialService userMaterialService, UserMaterialAssembler userMaterialAssembler) {
        this.userMaterialService = userMaterialService;
        this.userMaterialAssembler = userMaterialAssembler;
    }

    @GetMapping("list")
    @PreAuthorize("hasAnyRole('MEMBER','MODERATOR','ADMIN')")
    public ResponseEntity<List<EntityModel<UserMaterialDto>>> getUserMaterials() {
        List<UserMaterial> userMaterialList = userMaterialService.findAllUserMaterials();
        List<UserMaterialDto> userMaterialDtoList = userMaterialList
                .stream()
                .map(UserMaterialDto::fromEntity)
                .toList();

        List<EntityModel<UserMaterialDto>> listUserMaterialModel = userMaterialDtoList
                .stream()
                .map(userMaterialAssembler::toModel)
                .toList();

        return ResponseEntity.ok(listUserMaterialModel);
    }

}
