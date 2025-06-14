package be.brahms.rent_serve.services.impl;

import be.brahms.rent_serve.repositories.MaterialRepository;
import be.brahms.rent_serve.services.MaterialService;
import org.springframework.stereotype.Service;

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
}
