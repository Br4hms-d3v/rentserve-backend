package be.brahms.rent_serve.repositories;

import be.brahms.rent_serve.models.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    /**
     * Get all categories that have at least one material.
     * <p>
     * This method returns a list of categories.
     * It only includes categories that are linked to materials.
     * The list is sorted by the category name (A to Z)
     *
     * @return a list of categories by material
     */
    @Query("SELECT DISTINCT c FROM Category c JOIN c.materials m ORDER BY c.nameCategory ASC")
    List<Category> findAllCategoriesForMaterial();


    /**
     * Get all categories that have at least one service.
     * <p>
     * This method returns a list of categories.
     * It only includes categories that are linked to service.
     * The list is sorted by the category name (A to Z)
     *
     * @return a list of categories by service
     */
    @Query("SELECT DISTINCT c FROM Category c JOIN c.favours f ORDER BY c.nameCategory ASC")
    List<Category> findAllCategoriesForService();
}
