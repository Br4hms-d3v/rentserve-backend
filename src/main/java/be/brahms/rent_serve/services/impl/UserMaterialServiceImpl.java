package be.brahms.rent_serve.services.impl;

import be.brahms.rent_serve.exceptions.material.MaterialNotFoundException;
import be.brahms.rent_serve.exceptions.userMaterial.UserMaterialException;
import be.brahms.rent_serve.exceptions.userMaterial.UserMaterialNotFoundException;
import be.brahms.rent_serve.models.entities.Material;
import be.brahms.rent_serve.models.entities.UserMaterial;
import be.brahms.rent_serve.repositories.MaterialRepository;
import be.brahms.rent_serve.repositories.UserMaterialRepository;
import be.brahms.rent_serve.services.UserMaterialService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service implementation for managing user material.
 * Uses UserMaterialRepository to perform database operations.
 */
@Service
public class UserMaterialServiceImpl implements UserMaterialService {

    private final UserMaterialRepository userMaterialRepository;
    private final MaterialRepository materialRepository;

    /**
     * Constructor to create UserMaterialServiceImpl with UserMaterialRepository.
     *
     * @param userMaterialRepository the repository to access user material data
     */
    public UserMaterialServiceImpl(UserMaterialRepository userMaterialRepository, MaterialRepository materialRepository) {
        this.userMaterialRepository = userMaterialRepository;
        this.materialRepository = materialRepository;
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

    public UserMaterial findUserMaterialById(long userMaterialId) {
        return userMaterialRepository.findById(userMaterialId).orElseThrow(UserMaterialNotFoundException::new);
    }

    @Override
    public UserMaterial updateUserMaterial(long id, UserMaterial userMaterial) {

        UserMaterial userMaterialExisting = userMaterialRepository.findById(id).orElseThrow(UserMaterialNotFoundException::new);

        Long existingMaterialId = userMaterialExisting.getMaterial() != null ? userMaterialExisting.getMaterial().getId() : null;
        Long newMaterialId = userMaterial.getMaterial() != null ? userMaterial.getMaterial().getId() : null;

        if (newMaterialId != null && !newMaterialId.equals(existingMaterialId)) {

            Material newMaterial = materialRepository.findById(newMaterialId)
                    .orElseThrow(MaterialNotFoundException::new);
            userMaterial.setMaterial(newMaterial);
        } else {

            userMaterial.setMaterial(userMaterialExisting.getMaterial());
        }

        userMaterial.setId(userMaterialExisting.getId());
        userMaterial.setDescriptionMaterial(userMaterial.getDescriptionMaterial());
        userMaterial.setStateMaterial(userMaterial.getStateMaterial());
        userMaterial.setPriceHourMaterial(userMaterial.getPriceHourMaterial());
        userMaterial.setAvailable(userMaterial.isAvailable());

        userMaterial.setUser(userMaterialExisting.getUser());
        return userMaterialRepository.save(userMaterial);
    }

    public List<UserMaterial> listUserMaterialByUser(long userId, boolean availableOrNot) {

        // TODO if exist user id continue else exception
        // ... code here ...

        List<UserMaterial> listMaterialOwner = userMaterialRepository.findAllMaterialByOwner(userId, availableOrNot);

        if (listMaterialOwner.isEmpty()) {
            throw new UserMaterialException("La liste est vide");
        }

        return listMaterialOwner;
    }
}
