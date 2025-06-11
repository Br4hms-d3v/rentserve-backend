package be.brahms.rent_server.hateaos;

import be.brahms.rent_server.controllers.UserController;
import be.brahms.rent_server.models.dtos.UserDto;
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
     * This method creates a model for a user.
     *
     * @param user The user data we want to change to a model.
     * @return A model with user data and a link to the user.
     */
    @Override
    public EntityModel<UserDto> toModel(UserDto user) {
        return EntityModel.of(user,
                linkTo(methodOn(UserController.class).getUser(user.id())).withSelfRel());
    }
}
