package be.brahms.rent_serve.repositories;

import be.brahms.rent_serve.models.entities.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for managing Material entities.
 * Provides basic CRUD operations and more using JpaRepository.
 */
@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
}
