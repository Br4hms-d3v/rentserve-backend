package be.brahms.rent_serve.repositories;

import be.brahms.rent_serve.models.entities.SecondResidence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for managing SecondeResidence entities.
 * Provides basic CRUD operations and more using JpaRepository.
 */
@Repository
public interface SecondResidenceRepository extends JpaRepository<SecondResidence, Long> {
}
