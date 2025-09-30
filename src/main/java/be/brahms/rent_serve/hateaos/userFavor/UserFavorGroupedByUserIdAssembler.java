package be.brahms.rent_serve.hateaos.userFavor;

import be.brahms.rent_serve.controllers.UserFavorController;
import be.brahms.rent_serve.models.dtos.userFavor.UserFavourGroupedByFavourDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * This class helps to create an {@link EntityModel} from a {@link UserFavourGroupedByFavourDto}.
 * It adds a link to get all favours for a user (HATEOAS style).
 */
@Component
public class UserFavorGroupedByUserIdAssembler {

    /**
     * Empty constructor. No special setup is needed.
     */
    public UserFavorGroupedByUserIdAssembler() {
    }

    /**
     * Create an {@link EntityModel} from the given {@link UserFavourGroupedByFavourDto}.
     * It also adds a link to get all favours for the user.
     *
     * @param userFavourGroupedByFavour the DTO object with favour information
     * @return an {@link EntityModel} with the DTO and a link
     */
    public static EntityModel<UserFavourGroupedByFavourDto> toModel(UserFavourGroupedByFavourDto userFavourGroupedByFavour) {
        // Add a link to the controller method that gets all favours for a user
        return EntityModel.of(userFavourGroupedByFavour,
                linkTo(methodOn(UserFavorController.class).getUserFavorByUserId(userFavourGroupedByFavour.favor().id())).withRel("List favour from User")
        );
    }

}
