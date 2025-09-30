package be.brahms.rent_serve.hateaos.userFavor;

import be.brahms.rent_serve.controllers.UserFavorController;
import be.brahms.rent_serve.models.dtos.userFavor.UserFavorByIdDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Assembles a representation model for the UserFavorByIdDto class. This class is responsible for converting
 * UserFavorByIdDto objects into a hypermedia-supported EntityModel by providing the necessary self-links
 * or additional navigation links.
 * <p>
 * Used to manage hypermedia, such as adding links to API representations for UserFavorByIdDto resources.
 * <p>
 * This class is designated as a Spring component and allows for automatic detection and dependency injection.
 */
@Component
public class UserFavorIdAssembler implements RepresentationModelAssembler<UserFavorByIdDto, EntityModel<UserFavorByIdDto>> {

    /**
     * Default constructor for UserFavorIdAssembler.
     */
    public UserFavorIdAssembler() {
    }

    /**
     * Converts a UserFavorByIdDto object into an EntityModel, adding a HATEOAS link to the "getUserFavorById" endpoint.
     *
     * @param userFavor The UserFavorByIdDto instance to be converted into an EntityModel.
     * @return An EntityModel instance encapsulating the UserFavorByIdDto and its corresponding HATEOAS link.
     */
    @Override
    public EntityModel<UserFavorByIdDto> toModel(UserFavorByIdDto userFavor) {
        return EntityModel.of(userFavor,
                linkTo(methodOn(UserFavorController.class).getUserFavorById(userFavor.favor().id())).withRel("Get an user favor")
        );
    }
}
