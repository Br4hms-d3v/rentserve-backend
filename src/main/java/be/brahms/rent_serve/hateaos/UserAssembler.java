package be.brahms.rent_serve.hateaos;

import be.brahms.rent_serve.controllers.UserController;
import be.brahms.rent_serve.models.dtos.user.UserDto;
import be.brahms.rent_serve.models.dtos.user.UserRoleDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * UserAssembler is a class that helps to convert UserDto objects
 * into EntityModel objects. It creates models with links for user data.
 * <p>
 * This class uses a default constructor.
 */
@Component
public class UserAssembler implements RepresentationModelAssembler<UserDto, EntityModel<UserDto>> {

    /**
     * Default constructor for UserAssembler.
     */
    public UserAssembler() {
    }

    /**
     * Convert a UserDto to an EntityModel with HATEOAS links.
     * <p>
     * This method adds useful links to the UserDto,
     * like a link to the user itself and to the user list.
     *
     * @param user the user data to wrap
     * @return an EntityModel with the user data and HATEOAS links
     */
    @Override
    public EntityModel<UserDto> toModel(UserDto user) {
        return EntityModel.of(user,
                linkTo(methodOn(UserController.class).getUser(user.id())).withSelfRel(),
                linkTo(methodOn(UserController.class).getUsers()).withRel("List of all users"),
                linkTo(methodOn(UserController.class).getUserEdit(user.id(), null)).withRel("Edit the user"),
                linkTo(methodOn(UserController.class).getUpdatePassword(user.id(), null)).withRel("Change password"),
                linkTo(methodOn(UserController.class).deleteUser(user.id())).withRel("Delete the user")
        );
    }

    /**
     * Make a model with links for one user.
     *
     * @param user the user data
     * @return the user model with link
     */
    public EntityModel<UserRoleDto> toModel(UserRoleDto user) {
        return EntityModel.of(user,
                linkTo(methodOn(UserController.class).getUsersByRole((user.role()))).withRel("List of users by role")
        );
    }
}
