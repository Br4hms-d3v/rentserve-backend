package be.brahms.rent_serve.services.impl;

import be.brahms.rent_serve.repositories.SecondResidenceRepository;
import be.brahms.rent_serve.services.SecondResidenceService;
import org.springframework.stereotype.Service;

/**
 * Service implementation for managing seconde residence.
 * Uses SecondeResidenceRepository to perform database operations.
 */
@Service
public class SecondResidenceServiceImpl implements SecondResidenceService {

    private final SecondResidenceRepository secondResidenceRepository;

    /**
     * Constructor to create SecondeResidenceServiceImpl with SecondeResidenceRepository.
     *
     * @param secondResidenceRepository the repository to access seconde residence data
     */
    public SecondResidenceServiceImpl(SecondResidenceRepository secondResidenceRepository) {
        this.secondResidenceRepository = secondResidenceRepository;
    }
}
