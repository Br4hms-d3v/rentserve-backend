package be.brahms.rent_serve.repositories;

import be.brahms.rent_serve.models.entities.Favor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for managing Favor entities.
 * Provides basic CRUD operations and more using JpaRepository.
 */
@Repository
public interface FavorRepository extends JpaRepository<Favor, Long> {

    /**
     * The method check if the service exists
     *
     * @param nameFavor The name of favor
     * @return true if the name exists or false if it doesn't exist
     */
    boolean existsFavorByNameFavor(String nameFavor);

    /**
     * Make a list of favour, but it's group by category name
     *
     * @param categoryName The name of category
     * @return a list of favour grouped by category
     */
    @Query("SELECT f FROM Favor f JOIN f.category c WHERE c.nameCategory = :categoryName")
    List<Favor> findByCategoryName(@Param("categoryName") String categoryName);
}
