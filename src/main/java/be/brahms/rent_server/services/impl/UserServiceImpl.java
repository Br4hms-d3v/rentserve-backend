package be.brahms.rent_server.services.impl;

import be.brahms.rent_server.repositories.UserRepository;
import be.brahms.rent_server.services.UserService;
import org.springframework.stereotype.Service;

/**
 * Service implementation for managing users.
 * Uses UserRepository to perform database operations.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    /**
     * Constructor to create UserServiceImpl with UserRepository.
     *
     * @param userRepository the repository to access user data
     */
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
