package be.brahms.rent_server.hateaos;

import be.brahms.rent_server.controllers.UserController;
import be.brahms.rent_server.models.dtos.UserDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;

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
                linkTo(methodOn(UserController.class).getUsers()).withRel("List of all users")
        );
    }
}
