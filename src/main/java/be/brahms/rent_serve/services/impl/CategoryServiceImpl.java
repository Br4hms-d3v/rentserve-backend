package be.brahms.rent_serve.services.impl;

import be.brahms.rent_serve.exceptions.category.CategoryException;
import be.brahms.rent_serve.exceptions.category.CategoryExistException;
import be.brahms.rent_serve.exceptions.category.CategoryNotFoundException;
import be.brahms.rent_serve.models.entities.Category;
import be.brahms.rent_serve.repositories.CategoryRepository;
import be.brahms.rent_serve.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation for managing category.
 * Uses CategoryRepository to perform database operations.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    /**
     * Constructor to create CategoryServiceImpl with CategoryRepository.
     *
     * @param categoryRepository the repository to access category data
     */
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * This method display a list of categories
     *
     * @return the list of categories
     */
    public List<Category> findAllCategories() {

        List<Category> categories = categoryRepository.findAll();

        if (categories.isEmpty()) {
            throw new CategoryException("La liste est vide");
        }
        return categories;
    }

    /**
     * This methode create a new category
     * Before to create a new category,
     * check if a name of category already exist
     * if is true, send an exception
     * else save on DB
     *
     * @param category the creation a new category
     * @return the saved category
     * @throws CategoryExistException if the name of category exist send an exception with message
     */
    public Category create(Category category) {

        // Check if the category already exist before to create
        if (categoryRepository.existsByNameCategory(category.getNameCategory())) {
            throw new CategoryExistException();
        }

        return categoryRepository.save(category);
    }

    /**
     * This methode update a category
     * Before to edit a category,
     * check if a name of category already exist
     * if is true, send an exception
     * after save the new name
     *
     * @param category the edit category
     * @return the saved category
     * @throws CategoryExistException if the name of category exist send an exception with message
     */
    public Category updateCategory(long id, Category category) {

        Category updatedCategory = categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
        boolean categoryExist = categoryRepository.existsByNameCategory(category.getNameCategory());

        // Check if the name of category is similar
        if (categoryExist && updatedCategory.getNameCategory().equals(category.getNameCategory())) {
            throw new CategoryExistException();
        }

        // Save the name of Category updated to DB
        updatedCategory.setNameCategory(category.getNameCategory());

        return categoryRepository.save(updatedCategory);
    }

    /**
     * This methode update a category
     * Before to edit a category,
     * check if a name of category already exist
     * if is true, send an exception
     * after save the new name
     *
     * @param id the id's category
     * @return the delete category
     * @throws CategoryNotFoundException if the name of category exist send an exception with message
     */
    public Category deleteCategory(long id) {

        Category deleteCategory = categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
        boolean categoryExist = categoryRepository.existsByNameCategory(deleteCategory.getNameCategory());

        if (!categoryExist) {
            throw new CategoryNotFoundException();
        }
        categoryRepository.delete(deleteCategory);

        return deleteCategory;

    }

    /**
     * This method display a list
     * only categories from material
     *
     * @return the list of categories
     * @throws CategoryException if the list of categories are empty
     */
    @Override
    public List<Category> listAllCategoriesForMaterial() {
        List<Category> categoriesForMaterial = categoryRepository.findAllCategoriesForMaterial();

        if (categoriesForMaterial.isEmpty()) {
            throw new CategoryException("La liste est vide");
        }
        return categoriesForMaterial;
    }

    /**
     * This method display a list
     * only categories from service
     *
     * @return the list of categories
     * @throws CategoryException if the list of categories are empty
     */
    @Override
    public List<Category> listAllCategoriesForService() {
        List<Category> categoriesForService = categoryRepository.findAllCategoriesForService();

        if (categoriesForService.isEmpty()) {
            throw new CategoryException("La liste est vide");
        }
        return categoriesForService;
    }

    /**
     * This method display a list by search
     * only categories from material
     *
     * @return a list from search of categories
     * @throws CategoryException if the search is empty send a message
     */
    @Override
    public List<Category> searchCategoriesForMaterial(String materialCategory) {
        List<Category> categoriesSearchMaterial = categoryRepository.searchCategoriesForMaterial(materialCategory);

        if (categoriesSearchMaterial.isEmpty()) {
            throw new CategoryException("Entrez une catégorie");
        }

        return categoriesSearchMaterial;
    }

    /**
     * This method display a list by search
     * only categories from service
     *
     * @return a list from search of categories
     * @throws CategoryException if the search is empty send a message
     */
    @Override
    public List<Category> searchCategoriesForService(String serviceCategory) {
        List<Category> categoriesSearchService = categoryRepository.searchCategoriesForService(serviceCategory);

        if (categoriesSearchService.isEmpty()) {
            throw new CategoryException("Entrez une catégorie");
        }

        return categoriesSearchService;
    }


}
