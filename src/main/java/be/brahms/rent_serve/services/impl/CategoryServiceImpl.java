package be.brahms.rent_serve.services.impl;

import be.brahms.rent_serve.exceptions.category.CategoryException;
import be.brahms.rent_serve.exceptions.category.CategoryExistException;
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
     */
    public Category create(Category category) {

        // Check if the category already exist before to create
        if (categoryRepository.existsByNameCategory(category.getNameCategory())) {
            throw new CategoryExistException();
        }

        return categoryRepository.save(category);
    }


}
