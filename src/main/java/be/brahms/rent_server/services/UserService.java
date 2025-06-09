package be.brahms.rent_server.services;

import be.brahms.rent_server.models.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

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
}
