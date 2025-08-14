package be.brahms.rent_serve.hateaos;

import be.brahms.rent_serve.controllers.MaterialController;
import be.brahms.rent_serve.models.dtos.material.MaterialByIdDto;
import be.brahms.rent_serve.models.dtos.material.MaterialDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class MaterialAssembler implements RepresentationModelAssembler<MaterialDto, EntityModel<MaterialDto>> {

    /**
     * Default constructor for MaterialAssembler
     */
    public MaterialAssembler() {
    }

    /**
     * This method creates a model of one material with links.
     *
     * @param material The material data.
     * @return A model with the material and two links:
     * one to get all materials, and one to get this material by its ID.
     */
    @Override
    public EntityModel<MaterialDto> toModel(MaterialDto material) {
        return EntityModel.of(material,
                linkTo(methodOn(MaterialController.class).getMaterials()).withRel("List of all material"),
                linkTo(methodOn(MaterialController.class).getMaterial(material.id())).withRel("Get material by ID")
        );
    }

    /**
     * This method creates a model of one material with links.
     *
     * @param material The material data
     * @return A model with material and two links:
     * 1:   get all material
     * 2:   get one material
     */
    public EntityModel<MaterialByIdDto> toModel(MaterialByIdDto material) {
        return EntityModel.of(material,
                linkTo(methodOn(MaterialController.class).getMaterials()).withRel("List of all material"),
                linkTo(methodOn(MaterialController.class).getMaterial(material.id())).withRel("Get material by ID")
        );
    }
}
