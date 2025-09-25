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

/**
 * UserMaterialAssembler is a class that helps to convert UserMaterialDto objects
 * into EntityModel objects. It creates models with links for user data.
 * <p>
 * This class uses a default constructor
 * * Display link to read, update or delete
 * * Get UserMaterial by ID
 * * Get UserMaterial By ID for deleting
 */
@Component
public class UserMaterialAssembler implements RepresentationModelAssembler<UserMaterialDto, EntityModel<UserMaterialDto>> {

    /**
     * Default constructor for UserMaterialAssembler.
     */
    public UserMaterialAssembler() {
    }

    /**
     * Creates a model for one user material.
     * This model adds links to do actions with this material.
     *
     * @param userMaterial The user material data.
     * @return A model with the material and links to use it (get list, update, delete, etc.).
     * @throws RuntimeException If the user is not allowed to access some links.
     */
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

    /**
     * Creates a model for one user material by its ID.
     * This model has a link to get the same material again.
     *
     * @param userMaterial The user material data with ID.
     * @return A model with the material and a link to itself.
     */
    public EntityModel<UserMaterialByIdDto> toModel(UserMaterialByIdDto userMaterial) {

        return EntityModel.of(userMaterial,
                linkTo(methodOn(UserMaterialController.class).getUserMaterial(userMaterial.id())).withSelfRel()
        );
    }

    /**
     * Creates a model for deleting a user material.
     * This model has a link to delete the user material.
     *
     * @param userMaterialDeleteDto The data about the user material to delete.
     * @return A model with the delete data and a link to delete the material.
     */
    public EntityModel<UserMaterialDeleteDto> toModel(UserMaterialDeleteDto userMaterialDeleteDto) {
        UserMaterial userMaterial = new UserMaterial();
        return EntityModel.of(userMaterialDeleteDto,
                linkTo(methodOn(UserMaterialController.class).deleteUserMaterial(userMaterial.getId())).withRel("Delete a usermaterial")
        );
    }
}
