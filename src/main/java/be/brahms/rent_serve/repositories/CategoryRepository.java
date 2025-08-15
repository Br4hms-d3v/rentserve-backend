package be.brahms.rent_serve.repositories;

import be.brahms.rent_serve.models.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    Category findByNameCategory(String nameCategory);

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

    /**
     * Search the category by name exclusively for the material.
     * <p>
     * This method sends a category or a list of categories.
     * Only categories entered by the user in the category material are included.
     * The list is sorted by the category name (A to Z)
     *
     * @param materialCategory The word of search by user
     * @return to a search list that lists categories by material.
     */
    @Query("SELECT DISTINCT c FROM Category c JOIN c.materials m WHERE c.nameCategory ILIKE %:materialCategory% ORDER BY c.nameCategory ASC")
    List<Category> searchCategoriesForMaterial(@Param("materialCategory") String materialCategory);

    /**
     * Search the category by name exclusively for the service.
     * <p>
     * This method sends a category or a list of categories.
     * Only categories entered by the user in the category service are included.
     * The list is sorted by the category name (A to Z)
     *
     * @param serviceCategory the word of search by user
     * @return to a search list that lists categories by service.
     */
    @Query("SELECT DISTINCT c FROM Category c JOIN c.favours f WHERE c.nameCategory ILIKE %:serviceCategory% ORDER BY c.nameCategory ASC")
    List<Category> searchCategoriesForService(@Param("serviceCategory") String serviceCategory);
}
