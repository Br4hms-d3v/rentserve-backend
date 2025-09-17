package be.brahms.rent_serve.services.impl;

import be.brahms.rent_serve.exceptions.userMaterial.UserMaterialException;
import be.brahms.rent_serve.models.entities.UserMaterial;
import be.brahms.rent_serve.repositories.UserMaterialRepository;
import be.brahms.rent_serve.services.UserMaterialService;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<UserMaterial> findAllUserMaterials() {
        List<UserMaterial> userMaterials = userMaterialRepository.findAll();

        if (userMaterials.isEmpty()) {
            throw new UserMaterialException("La liste est vide");
        }

        return userMaterials;
    }

    public List<UserMaterial> listUserMaterialAvailable() {
        List<UserMaterial> userMaterialsAvailable = userMaterialRepository.findAllMaterialAvailable();

        if (userMaterialsAvailable.isEmpty()) {
            throw new UserMaterialException("La liste est vide");
        }

        return userMaterialsAvailable;
    }

    public List<UserMaterial> listUserMaterialNotAvailable() {
        List<UserMaterial> userMaterialsNotAvailable = userMaterialRepository.findAllMaterialNotAvailable();

        if (userMaterialsNotAvailable.isEmpty()) {
            throw new UserMaterialException("La liste est vide");
        }

        return userMaterialsNotAvailable;
    }
}
