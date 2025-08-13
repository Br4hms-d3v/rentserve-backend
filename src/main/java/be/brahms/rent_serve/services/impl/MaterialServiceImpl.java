package be.brahms.rent_serve.services.impl;

import be.brahms.rent_serve.exceptions.material.MaterialNotFoundException;
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

    @Override
    public List<Material> findAllMaterials() {
        return materialRepository.findAll();
    }

    @Override
    public Material findById(long id) {

        return materialRepository.findById(id).orElseThrow(MaterialNotFoundException::new);
    }
}
