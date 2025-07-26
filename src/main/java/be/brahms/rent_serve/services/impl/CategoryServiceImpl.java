package be.brahms.rent_serve.services.impl;

import be.brahms.rent_serve.exceptions.category.CategoryException;
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
}
