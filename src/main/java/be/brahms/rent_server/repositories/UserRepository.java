package be.brahms.rent_server.repositories;

import be.brahms.rent_server.enums.Role;
import be.brahms.rent_server.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    /**
     * Find a user by email or pseudo.
     *
     * @param email  the user's email
     * @param pseudo the user's pseudo (nickname)
     * @return an optional user if found, or empty if not found
     */
    Optional<User> findByEmailOrPseudo(String email, String pseudo);

    /**
     * Finds a user by email.
     *
     * @param email The email.
     * @return The user.
     */
    User findByEmail(String email);

    /**
     * Find users by their role.
     *
     * @param role the role to search for
     * @return list of users with the given role
     */
    @Query("SELECT u FROM User u WHERE u.role = :role")
    List<User> listUsersByRole(@Param("role") Role role);
}
