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

    /**
     * This constructor is used to inject the necessary services for handling category-related request.
     *
     * @param categoryService   the service used for category
     * @param categoryAssembler the assembler used to convert Category object to into CategoryDto models
     */
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
     * This method get a category by his ID
     *
     * @param id the ID of the category
     * @return a name of category choice by his ID
     */
    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('Moderator','ADMIN')")
    public ResponseEntity<EntityModel<CategoryDto>> getCategory(@PathVariable long id) {
        Category category = categoryService.findById(id);
        CategoryDto categoryDto = CategoryDto.fromEntity(category);

        return ResponseEntity.ok().body(categoryAssembler.toModel(categoryDto));
    }

    /**
     * Create a new category
     * This method return a new category
     *
     * @param form The form contain data to create a new category
     * @return a responseEntity with the new category
     */
    @PostMapping("new/category")
    @PreAuthorize("hasAnyRole('MEMBER','MODERATOR','ADMIN')")
    public ResponseEntity<EntityModel<CategoryDto>> create(@RequestBody @Valid CategoryForm form) {
        Category createCategory = categoryService.create(form.toEntity());
        CategoryDto categoryDto = CategoryDto.fromEntity(createCategory);

        EntityModel<CategoryDto> categoryModel = categoryAssembler.toModel(categoryDto);

        return ResponseEntity.ok().body(categoryModel);

    }

    /**
     * Update the category by his id
     *
     * <p>This method allows a user with role Moderator or Admin to edit the name</p>
     * Check before update the name of category the name is already exist.
     *
     * @param id   the ID of the category
     * @param form the new data to edit the category
     * @return ResponseEntity a response to name of category edited
     */
    @PutMapping("{id}/edit")
    @PreAuthorize("hasAnyRole('MODERATOR','ADMIN')")
    public ResponseEntity<EntityModel<CategoryDto>> update(@PathVariable Long id, @RequestBody @Valid CategoryForm form) {
        Category editCategory = categoryService.updateCategory(id, form.toEntity());
        CategoryDto categoryDto = CategoryDto.fromEntity(editCategory);
        EntityModel<CategoryDto> categoryModel = categoryAssembler.toModel(categoryDto);
        return ResponseEntity.ok().body(categoryModel);
    }

    /**
     * Delete the category.
     *
     * @param id the category's ID
     * @return the delete category with links
     */
    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EntityModel<CategoryDto>> delete(@PathVariable Long id) {
        Category deleteCategory = categoryService.deleteCategory(id);
        CategoryDto categoryDto = CategoryDto.fromEntity(deleteCategory);
        categoryAssembler.toModel(categoryDto);
        return ResponseEntity.ok().build();
    }

    /**
     * Get a list of categories .
     * <p>
     * This method returns a list of all categories based on material.
     * Each category is converted to a CategoryDto (Data Transfer Object).
     * Each CategoryDto is wrapped inside an EntityModel with HATEOAS links.
     *
     * @return ResponseEntity with a list of category models
     */
    @GetMapping("material")
    @PreAuthorize("hasAnyRole('MEMBER', 'MODERATOR', 'ADMIN')")
    public ResponseEntity<List<EntityModel<CategoryDto>>> getCategoriesForMaterial() {
        List<Category> categoriesListMaterial = categoryService.listAllCategoriesForMaterial();
        List<CategoryDto> categoryDtoListMaterial = categoriesListMaterial
                .stream()
                .map(CategoryDto::fromEntity)
                .toList();

        List<EntityModel<CategoryDto>> categoryListMaterialModel = categoryDtoListMaterial
                .stream()
                .map(categoryAssembler::toModel)
                .toList();

        return ResponseEntity.ok().body(categoryListMaterialModel);
    }

    /**
     * Get a list of categories .
     * <p>
     * This method returns a list of all categories based on service.
     * Each category is converted to a CategoryDto (Data Transfer Object).
     * Each CategoryDto is wrapped inside an EntityModel with HATEOAS links.
     *
     * @return ResponseEntity with a list of category models
     */
    @GetMapping("service")
    @PreAuthorize("hasAnyRole('MEMBER', 'MODERATOR', 'ADMIN')")
    public ResponseEntity<List<EntityModel<CategoryDto>>> getCategoriesForService() {
        List<Category> categoriesListService = categoryService.listAllCategoriesForService();
        List<CategoryDto> categoryDtoListService = categoriesListService
                .stream()
                .map(CategoryDto::fromEntity)
                .toList();

        List<EntityModel<CategoryDto>> categoryListServiceModel = categoryDtoListService
                .stream()
                .map(categoryAssembler::toModel)
                .toList();

        return ResponseEntity.ok().body(categoryListServiceModel);
    }

    /**
     * Search categories for material
     *
     * @param nameCategoryMaterial the name of the material to search
     * @return a list of CategoryDto models that match the material category name
     */
    @GetMapping("search-material/{nameCategoryMaterial}")
    @PreAuthorize("hasAnyRole('MEMBER', 'MODERATOR', 'ADMIN')")
    public ResponseEntity<List<EntityModel<CategoryDto>>> searchCategoriesForMaterial(@PathVariable String nameCategoryMaterial) {
        List<Category> searchNameCategoriesForMaterial = categoryService.searchCategoriesForMaterial(nameCategoryMaterial);

        List<CategoryDto> categoryNameForMaterialDto = searchNameCategoriesForMaterial
                .stream()
                .map(CategoryDto::fromEntity)
                .toList();

        List<EntityModel<CategoryDto>> categoryNameForMaterialModel = categoryNameForMaterialDto
                .stream()
                .map(categoryAssembler::toModel)
                .toList();

        return ResponseEntity.ok().body(categoryNameForMaterialModel);
    }

    /**
     * Search categories for service
     *
     * @param nameCategoryService the name of the material to search
     * @return a list of CategoryDto models that match the material category name
     */
    @GetMapping("search-service/{nameCategoryService}")
    @PreAuthorize("hasAnyRole('MEMBER', 'MODERATOR', 'ADMIN')")
    public ResponseEntity<List<EntityModel<CategoryDto>>> searchCategoriesForService(@PathVariable String nameCategoryService) {
        List<Category> searchNameCategoriesForService = categoryService.searchCategoriesForService(nameCategoryService);

        List<CategoryDto> categoryNameForServiceDto = searchNameCategoriesForService
                .stream()
                .map(CategoryDto::fromEntity)
                .toList();

        List<EntityModel<CategoryDto>> categoryNameForServiceModel = categoryNameForServiceDto
                .stream()
                .map(categoryAssembler::toModel)
                .toList();

        return ResponseEntity.ok().body(categoryNameForServiceModel);
    }

}
