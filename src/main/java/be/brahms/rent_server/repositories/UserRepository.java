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

    /**
     * Check if a user exists with this email.
     *
     * @param email the email to check
     * @return true if email exists
     */
    boolean existsByEmail(String email); // Check if there is already a pseudo email.

    /**
     * Check if a user exists with this pseudo.
     *
     * @param pseudo the email to check
     * @return true if pseudo exists
     */
    boolean existsByPseudo(String pseudo); // Check if there is already a pseudo already.
}
