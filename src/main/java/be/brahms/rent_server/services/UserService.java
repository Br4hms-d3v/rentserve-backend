package be.brahms.rent_server.services;

import be.brahms.rent_server.enums.Role;
import be.brahms.rent_server.models.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * Service interface for managing user.
 * Defines business operations related to Bill entities.
 */
public interface UserService extends UserDetailsService {

    /**
     * This method saves a new user.
     *
     * @param user the user to register
     * @return the saved user
     */
    User register(User user);

    /**
     * To activate the user.
     *
     * @param email The user email.
     */
    void activateUser(String email);

    /**
     * This method connect the user
     *
     * @param user the user for log in
     * @return the connected user
     */
    User login(User user);

    /**
     * This method get a user by ID
     *
     * @param id from the user
     * @return data's from user by id if exist
     */
    User findById(long id);

    /**
     * This method get a list of users
     *
     * @return list of all users
     */
    List<User> findAllUsers();

    /**
     * Get all users with a specific role.
     *
     * @param role the role to filter users
     * @return list of users with the given role
     */
    List<User> findAllUsersByRole(Role role);

    /**
     * Updates a user by ID.
     *
     * @param id   the user's ID
     * @param user the new user data
     * @return the updated user
     */
    User updateUser(long id, User user);

    /**
     * Updates password .
     *
     * @param id   the user's ID
     * @param user the new password for the user
     * @return the updated password user
     */
    User updatePassword(long id, User user);

}
