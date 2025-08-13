package be.brahms.rent_serve.services.impl;

import be.brahms.rent_serve.exceptions.material.MaterialException;
import be.brahms.rent_serve.models.entities.Material;
import be.brahms.rent_serve.repositories.MaterialRepository;
import be.brahms.rent_serve.services.MaterialService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation for managing material.
 * Uses MaterialRepository to perform database operations.
 */
@Service
public class MaterialServiceImpl implements MaterialService {

    private final MaterialRepository materialRepository;

    /**
     * Constructor to create MaterialServiceImpl with MaterialRepository.
     *
     * @param materialRepository the repository to access material data
     */
    public MaterialServiceImpl(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

//    @Override
//    public List<Material> findAllMaterials() {
//
//        List<Material> materials = materialRepository.findAll();
//
//        // If the list of materials are empty send a message
//        if (materials.isEmpty()) {
//            throw new MaterialException("la liste est vide");
//        }
//        return materials;
//    }

    @Override
    public List<Material> findAllMaterials() {
        return materialRepository.findAllWithUserMaterialsAndPictures();
    }
}
