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


@Component
public class UserFavorAssembler implements RepresentationModelAssembler<UserFavorDto, EntityModel<UserFavorDto>> {

    public UserFavorAssembler() {
    }

    @Override
    public EntityModel<UserFavorDto> toModel(UserFavorDto userFavor) {
        return EntityModel.of(userFavor,
                linkTo(methodOn(UserFavorController.class).getUserFavour()).withRel("List of user favour"),
                linkTo(methodOn(UserFavorController.class).deleteUserFavor(userFavor.id())).withRel("Delete a user favor")
        );
    }

    public EntityModel<UserFavorDeleteDto> toModel(UserFavorDeleteDto userFavorDeleteDto) {
        UserFavor userFavor = new UserFavor();
        return EntityModel.of(userFavorDeleteDto,
                linkTo(methodOn(UserFavorController.class).deleteUserFavor(userFavor.getId())).withRel("Delete a user favor"));
    }
}
