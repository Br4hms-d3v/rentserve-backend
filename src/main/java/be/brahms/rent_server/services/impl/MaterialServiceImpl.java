package be.brahms.rent_server.services.impl;

import be.brahms.rent_server.repositories.MaterialRepository;
import be.brahms.rent_server.services.MaterialService;
import org.springframework.stereotype.Service;

@Service
public class MaterialServiceImpl implements MaterialService {

    private final MaterialRepository materialRepository;

    public MaterialServiceImpl(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }
}
