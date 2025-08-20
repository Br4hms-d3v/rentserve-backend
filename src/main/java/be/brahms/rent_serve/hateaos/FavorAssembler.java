package be.brahms.rent_serve.hateaos;

import be.brahms.rent_serve.controllers.FavorController;
import be.brahms.rent_serve.models.dtos.favor.FavorByIdDto;
import be.brahms.rent_serve.models.dtos.favor.FavorDto;
import be.brahms.rent_serve.models.forms.favor.FavorForm;
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
        FavorForm favorForm = new FavorForm(favor.nameFavor(), favor.nameFavor());

        return EntityModel.of(favor,
                linkTo(methodOn(FavorController.class).getFavour()).withRel("List of all favour"),
                linkTo(methodOn(FavorController.class).getFavourById(favor.id())).withRel("Get favor by ID"),
                linkTo(methodOn(FavorController.class).createFavor(favorForm)).withRel("Create a new Favor"),
                linkTo(methodOn(FavorController.class).deleteFavor(favor.id())).withRel("Delete a favor")
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
        FavorForm favorForm = new FavorForm(favor.nameFavor(), favor.nameFavor());

        return EntityModel.of(favor,
                linkTo(methodOn(FavorController.class).getFavour()).withRel("List of all favour"),
                linkTo(methodOn(FavorController.class).getFavourById(favor.id())).withRel("Get favor by ID"),
                linkTo(methodOn(FavorController.class).createFavor(favorForm)).withRel("Create a new Favor"),
                linkTo(methodOn(FavorController.class).updateFavor(favor.id(), favorForm)).withRel("Edit the Favor"),
                linkTo(methodOn(FavorController.class).deleteFavor(favor.id())).withRel("Delete a favor"),
                linkTo(methodOn(FavorController.class).getAllFavorByNameCategory(favor.category())).withRel("List of favour by category name")
        );
    }
}
