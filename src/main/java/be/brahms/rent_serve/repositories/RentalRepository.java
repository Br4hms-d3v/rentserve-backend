package be.brahms.rent_serve.repositories;

import be.brahms.rent_serve.models.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for managing Rental entities.
 * Provides basic CRUD operations and more using JpaRepository.
 */
@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
}
