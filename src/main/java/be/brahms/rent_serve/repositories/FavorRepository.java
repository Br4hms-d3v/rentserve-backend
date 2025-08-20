package be.brahms.rent_serve.repositories;

import be.brahms.rent_serve.models.entities.Favor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
}
