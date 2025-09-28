package be.brahms.rent_serve.hateaos.userFavor;

import be.brahms.rent_serve.controllers.UserFavorController;
import be.brahms.rent_serve.models.dtos.userFavor.UserFavorByIdDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserFavorIdAssembler implements RepresentationModelAssembler<UserFavorByIdDto, EntityModel<UserFavorByIdDto>> {

    public UserFavorIdAssembler() {
    }

    @Override
    public EntityModel<UserFavorByIdDto> toModel(UserFavorByIdDto userFavor) {
        return EntityModel.of(userFavor,
                linkTo(methodOn(UserFavorController.class).getUserFavorById(userFavor.favor().id())).withRel("Get an user favor")
        );
    }
}
