package be.brahms.rent_serve.repositories;

import be.brahms.rent_serve.models.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for managing Category entities.
 * Provides basic CRUD operations and more using JpaRepository.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    /**
     * Check if a category exists with this name.
     *
     * @param nameCategory the name of category to check
     * @return true if name of category exists
     */
    boolean existsByNameCategory(String nameCategory);
}
