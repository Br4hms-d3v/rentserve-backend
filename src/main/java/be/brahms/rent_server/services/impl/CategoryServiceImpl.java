package be.brahms.rent_server.services.impl;

import be.brahms.rent_server.repositories.CategoryRepository;
import be.brahms.rent_server.services.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
}
