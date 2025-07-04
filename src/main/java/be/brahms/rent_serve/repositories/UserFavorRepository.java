package be.brahms.rent_serve.repositories;

import be.brahms.rent_serve.models.entities.UserFavor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for managing UserFavor entities.
 * Provides basic CRUD operations and more using JpaRepository.
 */
@Repository
public interface UserFavorRepository extends JpaRepository<UserFavor, Long> {
}
