package be.brahms.rent_serve.controllers;

import be.brahms.rent_serve.hateaos.CategoryAssembler;
import be.brahms.rent_serve.models.dtos.catetory.CategoryDto;
import be.brahms.rent_serve.models.entities.Category;
import be.brahms.rent_serve.models.forms.category.CategoryForm;
import be.brahms.rent_serve.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This controller manages categories
 * Display the list of categories
 */
@RestController
@RequestMapping("/api/categories/")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryAssembler categoryAssembler;

    public CategoryController(CategoryService categoryService, CategoryAssembler categoryAssembler) {
        this.categoryService = categoryService;
        this.categoryAssembler = categoryAssembler;
    }


    /**
     * Get all categories from the database
     * This method returns a list of all categories
     * Each category is converted to a CategoryDto
     * Each categoryDto is wrapped inside an EntityModel with HATEOAS links.
     *
     * @return ResponseEntity with a list of category model
     */
    @GetMapping("list")
    @PreAuthorize("hasAnyRole('MEMBER','Moderator','ADMIN')")
    public ResponseEntity<List<EntityModel<CategoryDto>>> getCategories() {
        List<Category> categoriesList = categoryService.findAllCategories();
        List<CategoryDto> categoryDtoList = categoriesList
                .stream()
                .map(CategoryDto::fromEntity)
                .toList();

        List<EntityModel<CategoryDto>> listCategoriesDtoModel = categoryDtoList
                .stream()
                .map(categoryAssembler::toModel)
                .toList();

        return ResponseEntity.ok().body(listCategoriesDtoModel);
    }

    /**
     * Create a new category
     */
    @PostMapping("new/category")
    @PreAuthorize("hasAnyRole('MODERATOR','ADMIN')")
    public ResponseEntity<EntityModel<CategoryDto>> create(@RequestBody @Valid CategoryForm form) {
        Category createCategory = categoryService.create(form.toEntity());
        CategoryDto categoryDto = CategoryDto.fromEntity(createCategory);

        EntityModel<CategoryDto> categoryModel = categoryAssembler.toModel(categoryDto);

        return ResponseEntity.ok().body(categoryModel);

    }

    /**
     * Edit category
     * */
//    public ResponseEntity<> {
//    }

    /**
     * Delete category
     * */
//    public ResponseEntity<> {
//    }

    /**
     * Select all categories by material
     * */
//    public ResponseEntity<> {
//    }

    /**
     * Select all categories by favor
     * */
//    public ResponseEntity<> {
//    }

    /**
     * Search categories for material
     * */
//    public ResponseEntity<> {
//    }

    /**
     * Search categories for favor
     * */
//    public ResponseEntity<> {
//    }

}
