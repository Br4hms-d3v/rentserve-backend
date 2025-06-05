package be.brahms.rent_server.services.impl;

import be.brahms.rent_server.repositories.CategoryRepository;
import be.brahms.rent_server.services.CategoryService;
import org.springframework.stereotype.Service;

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
}
