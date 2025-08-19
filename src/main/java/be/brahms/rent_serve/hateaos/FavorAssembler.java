package be.brahms.rent_serve.hateaos;

import be.brahms.rent_serve.controllers.FavorController;
import be.brahms.rent_serve.models.dtos.favor.FavorByIdDto;
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
                linkTo(methodOn(FavorController.class).getFavour()).withRel("List of all favour"),
                linkTo(methodOn(FavorController.class).getFavourById(favor.id())).withRel("Get favor by ID")
        );
    }

    /**
     * This method creates a model of one favor with links.
     *
     * @param favor The favor data
     * @return A model with favor and two links:
     * 1:   get all favour
     * 2:   get one favor
     * 3:   Create a new favor
     * 4:   Edit a favor
     * 5:   Delete a favor
     * 6:   List of favour group by category
     */
    public EntityModel<FavorByIdDto> toModel(FavorByIdDto favor) {

        return EntityModel.of(favor,
                linkTo(methodOn(FavorController.class).getFavourById(favor.id())).withRel("Get favor by ID")
        );
    }
}
