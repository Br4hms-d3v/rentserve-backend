package be.brahms.rent_serve.repositories;

import be.brahms.rent_serve.models.entities.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for managing Material entities.
 * Provides basic CRUD operations and more using JpaRepository.
 */
@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {

    @Query("SELECT m FROM Material m " +
            "LEFT JOIN FETCH m.userMaterials um " +
            "LEFT JOIN FETCH um.pictures p " +
            "ORDER BY m.id")
    List<Material> findAllWithUserMaterialsAndPictures();
}
