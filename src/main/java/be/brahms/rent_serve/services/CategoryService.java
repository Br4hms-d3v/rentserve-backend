package be.brahms.rent_serve.services;

import be.brahms.rent_serve.models.entities.Category;

import java.util.List;

/**
 * Service interface for managing category.
 * Defines business operations related to Category entities.
 */
public interface CategoryService {

    /**
     * This method get a list of categories
     *
     * @return list of all categories
     */
    List<Category> findAllCategories();

    /**
     * This method saves a new category.
     *
     * @param category the creation a new category
     * @return the saved category
     */
    Category create(Category category);

    /**
     * Update category
     *
     * @param id       the category's ID
     * @param category the name of category updated
     * @return the update name of category
     */
    Category updateCategory(long id, Category category);

    /**
     * Delete the category
     *
     * @param id the category's id
     * @return Delete the category
     */
    Category deleteCategory(long id);

    /**
     * List of all categories for only material
     *
     * @return list of categories
     */
    List<Category> listAllCategoriesForMaterial();

    /**
     * List of all categories for only service
     *
     * @return list of categories
     */
    List<Category> listAllCategoriesForService();

}
