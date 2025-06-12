package be.brahms.rent_server.services;

import be.brahms.rent_server.models.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

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

}
