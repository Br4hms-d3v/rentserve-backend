package be.brahms.rent_serve.hateaos;

import be.brahms.rent_serve.controllers.UserMaterialController;
import be.brahms.rent_serve.models.dtos.userMaterial.UserMaterialByIdDto;
import be.brahms.rent_serve.models.dtos.userMaterial.UserMaterialDeleteDto;
import be.brahms.rent_serve.models.dtos.userMaterial.UserMaterialDto;
import be.brahms.rent_serve.models.entities.UserMaterial;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.nio.file.AccessDeniedException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserMaterialAssembler implements RepresentationModelAssembler<UserMaterialDto, EntityModel<UserMaterialDto>> {

    public UserMaterialAssembler() {

    }

    @Override
    public EntityModel<UserMaterialDto> toModel(UserMaterialDto userMaterial) {

        try {
            return EntityModel.of(userMaterial,
                    linkTo(methodOn(UserMaterialController.class).getUserMaterials()).withRel("List of all material from users"),
                    linkTo(methodOn(UserMaterialController.class).getActivatedUserMaterials()).withRel("List of materials activated"),
                    linkTo(methodOn(UserMaterialController.class).getDeactivatedUserMaterials()).withRel("List of materials deactivated"),
                    linkTo(methodOn(UserMaterialController.class).updateUserMaterial(userMaterial.id(), null)).withRel("Update the user material").withType("PUT"),
                    linkTo(methodOn(UserMaterialController.class).deleteUserMaterial(userMaterial.id())).withRel("Delete the user material").withType("POST"));
        } catch (AccessDeniedException e) {
            throw new RuntimeException(e);
        }
    }

    public EntityModel<UserMaterialByIdDto> toModel(UserMaterialByIdDto userMaterial) {

        return EntityModel.of(userMaterial,
                linkTo(methodOn(UserMaterialController.class).getUserMaterial(userMaterial.id())).withSelfRel()
        );
    }

    public EntityModel<UserMaterialDeleteDto> toModel(UserMaterialDeleteDto userMaterialDeleteDto) {
        UserMaterial userMaterial = new UserMaterial();
        return EntityModel.of(userMaterialDeleteDto,
                linkTo(methodOn(UserMaterialController.class).deleteUserMaterial(userMaterial.getId())).withRel("Delete a usermaterial")
        );
    }
}
