package be.brahms.rent_server.repositories;

import be.brahms.rent_server.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for managing User entities.
 * Provides basic CRUD operations and more using JpaRepository.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
