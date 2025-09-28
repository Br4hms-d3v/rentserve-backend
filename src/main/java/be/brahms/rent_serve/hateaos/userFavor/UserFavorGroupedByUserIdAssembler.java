package be.brahms.rent_serve.hateaos.userFavor;

import be.brahms.rent_serve.controllers.UserFavorController;
import be.brahms.rent_serve.models.dtos.userFavor.UserFavourGroupedByFavourDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserFavorGroupedByUserIdAssembler {

    public UserFavorGroupedByUserIdAssembler(){}

    public static EntityModel<UserFavourGroupedByFavourDto> toModel(UserFavourGroupedByFavourDto userFavourGroupedByFavour) {
        return EntityModel.of(userFavourGroupedByFavour,
                linkTo(methodOn(UserFavorController.class).getUserFavorByUserId(userFavourGroupedByFavour.favor().id())).withRel("List favour from User")
        );
    }


}
