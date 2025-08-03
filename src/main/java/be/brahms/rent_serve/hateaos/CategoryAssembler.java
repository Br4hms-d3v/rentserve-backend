package be.brahms.rent_serve.hateaos;

import be.brahms.rent_serve.controllers.CategoryController;
import be.brahms.rent_serve.models.dtos.catetory.CategoryDto;
import be.brahms.rent_serve.models.forms.category.CategoryForm;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * CategoryAssembler is a class that helps to convert CategoryDto objects
 * into EntityModel objects. It creates models with links for category data.
 * <p>
 * This class uses a default constructor.
 */
@Component
public class CategoryAssembler implements RepresentationModelAssembler<CategoryDto, EntityModel<CategoryDto>> {

    /**
     * Default constructor for CategoryAssembler.
     */
    public CategoryAssembler() {
    }

    /**
     * Convert a CategoryDto to an EntityModel with HATEOAS links.
     * <p>
     * This method adds useful links to the CategoryDto,
     * a link for all categories or category by materials or services
     *
     * @param category the category data to wrap
     * @return an EntityModel with the category data and HATEOAS links
     */
    @Override
    public EntityModel<CategoryDto> toModel(CategoryDto category) {
        CategoryForm categForm = new CategoryForm(category.nameCategory());

        return EntityModel.of(category,
                linkTo(methodOn(CategoryController.class).getCategories()).withRel("List of all categories"),
                linkTo(methodOn(CategoryController.class).create(categForm)).withRel("Create a new category"),
                linkTo(methodOn(CategoryController.class).getCategory(category.id())).withRel("Get a category by id"),
                linkTo(methodOn(CategoryController.class).update(category.id(), categForm)).withRel("Edit categories"),
                linkTo(methodOn(CategoryController.class).delete(category.id())).withRel("Delete categories"),
                linkTo(methodOn(CategoryController.class).getCategoriesForMaterial()).withRel("A List of categories based on material"),
                linkTo(methodOn(CategoryController.class).getCategoriesForService()).withRel("A List of categories based on service"),
                linkTo(methodOn(CategoryController.class).searchCategoriesForMaterial(category.nameCategory())).withRel("Search a category for material"),
                linkTo(methodOn(CategoryController.class).searchCategoriesForService(category.nameCategory())).withRel("Search a category for service")
        );
    }
}
