package be.brahms.rent_serve.hateaos;

import be.brahms.rent_serve.controllers.FavorController;
import be.brahms.rent_serve.models.dtos.favor.FavorDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class FavorAssembler implements RepresentationModelAssembler<FavorDto, EntityModel<FavorDto>> {

    /**
     * Default constructor for FavorAssembler
     */
    public FavorAssembler() {
    }

    /**
     * This methode create a model for favor with links
     */
    @Override
    public EntityModel<FavorDto> toModel(FavorDto favor) {

        return EntityModel.of(favor,
                linkTo(methodOn(FavorController.class).getFavour()).withRel("List of all favour")
        );
    }
}
