package be.brahms.rent_server.services.impl;

import be.brahms.rent_server.repositories.SecondResidenceRepository;
import be.brahms.rent_server.services.SecondResidenceService;
import org.springframework.stereotype.Service;

@Service
public class SecondResidenceServiceImpl implements SecondResidenceService {

    private final SecondResidenceRepository secondResidenceRepository;

    public SecondResidenceServiceImpl(SecondResidenceRepository secondResidenceRepository) {
        this.secondResidenceRepository = secondResidenceRepository;
    }
}
