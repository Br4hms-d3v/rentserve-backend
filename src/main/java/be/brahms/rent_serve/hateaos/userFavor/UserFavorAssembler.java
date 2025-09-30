package be.brahms.rent_serve.hateaos.userFavor;

import be.brahms.rent_serve.controllers.UserFavorController;
import be.brahms.rent_serve.models.dtos.userFavor.UserFavorDeleteDto;
import be.brahms.rent_serve.models.dtos.userFavor.UserFavorDto;
import be.brahms.rent_serve.models.entities.UserFavor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * The UserFavorAssembler is responsible for converting UserFavorDto and UserFavorDeleteDto objects
 * into their corresponding EntityModel representations. It implements the RepresentationModelAssembler
 * interface to facilitate the association of hyperlinks with the respective user favor resources.
 * <p>
 * This class is used to provide hypermedia-driven navigation for user favor-related data, enabling
 * the inclusion of relational links in the API responses, such as retrieving the list of user favors
 * or deleting a specific user favor.
 */
@Component
public class UserFavorAssembler implements RepresentationModelAssembler<UserFavorDto, EntityModel<UserFavorDto>> {

    /**
     * Default constructor for UserFavorAssembler.
     */
    public UserFavorAssembler() {
    }

    /**
     * Convert a UserFavorDto to an EntityModel with HATEOAS links.
     * <p>
     * This method adds useful links to the UserFavorDto,
     * - a link for all user favors
     * - a link to delete a user favor
     * </p>
     *
     * @param userFavor the user favors data to wrap
     * @return an EntityModel with the user favor data and HATEOAS links
     */
    @Override
    public EntityModel<UserFavorDto> toModel(UserFavorDto userFavor) {
        return EntityModel.of(userFavor,
                linkTo(methodOn(UserFavorController.class).getUserFavour()).withRel("List of user favour"),
                linkTo(methodOn(UserFavorController.class).deleteUserFavor(userFavor.id())).withRel("Delete a user favor")
        );
    }

    /**
     * This method creates a model of one user favor with links.
     * A link to delete a user favor
     *
     * @param userFavorDeleteDto the user favor data
     * @return a model with user favor and a link to delete it
     */
    public EntityModel<UserFavorDeleteDto> toModel(UserFavorDeleteDto userFavorDeleteDto) {
        UserFavor userFavor = new UserFavor();
        return EntityModel.of(userFavorDeleteDto,
                linkTo(methodOn(UserFavorController.class).deleteUserFavor(userFavor.getId())).withRel("Delete a user favor"));
    }
}
