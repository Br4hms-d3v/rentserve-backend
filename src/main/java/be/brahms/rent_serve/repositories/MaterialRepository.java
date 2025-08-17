package be.brahms.rent_serve.repositories;

import be.brahms.rent_serve.models.entities.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Repository for managing Material entities.
 * Provides basic CRUD operations and more using JpaRepository.
 */
@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {

    /**
     * The method check if the material exist
     *
     * @param nameMaterial The name of material
     * @return true if the material exist  or false if the material doesn't exist
     */
    boolean existsMaterialByNameMaterial(String nameMaterial);

    /**
     * Make a list of material, but it's group by category name
     *
     * @param categoryName the name of category
     * @return a list of material grouped by name of category
     */
    @Query("SELECT m FROM Material m JOIN m.category c WHERE c.nameCategory = :categoryName")
    List<Material> findByCategoryName(@Param("categoryName") String categoryName);

}
