package be.brahms.rent_serve.repositories;

import be.brahms.rent_serve.models.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for managing Review entities.
 * Provides basic CRUD operations and more using JpaRepository.
 */
@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
