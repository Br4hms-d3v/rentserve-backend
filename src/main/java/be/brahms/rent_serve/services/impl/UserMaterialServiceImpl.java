package be.brahms.rent_serve.services.impl;

import be.brahms.rent_serve.repositories.UserMaterialRepository;
import be.brahms.rent_serve.services.UserMaterialService;
import org.springframework.stereotype.Service;

/**
 * Service implementation for managing user material.
 * Uses UserMaterialRepository to perform database operations.
 */
@Service
public class UserMaterialServiceImpl implements UserMaterialService {

    private final UserMaterialRepository userMaterialRepository;

    /**
     * Constructor to create UserMaterialServiceImpl with UserMaterialRepository.
     *
     * @param userMaterialRepository the repository to access user material data
     */
    public UserMaterialServiceImpl(UserMaterialRepository userMaterialRepository) {
        this.userMaterialRepository = userMaterialRepository;
    }
}
