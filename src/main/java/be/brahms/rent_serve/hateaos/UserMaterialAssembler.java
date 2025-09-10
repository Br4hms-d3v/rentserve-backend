package be.brahms.rent_serve.hateaos;

import be.brahms.rent_serve.controllers.UserMaterialController;
import be.brahms.rent_serve.models.dtos.userMaterial.UserMaterialDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserMaterialAssembler implements RepresentationModelAssembler<UserMaterialDto, EntityModel<UserMaterialDto>> {

    public UserMaterialAssembler() {

    }

    @Override
    public EntityModel<UserMaterialDto> toModel(UserMaterialDto userMaterial) {
        return EntityModel.of(userMaterial,
                linkTo(methodOn(UserMaterialController.class).getUserMaterials()).withRel("List of all material from users"));
    }
}
