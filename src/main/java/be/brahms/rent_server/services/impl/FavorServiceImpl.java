package be.brahms.rent_server.services.impl;

import be.brahms.rent_server.repositories.FavorRepository;
import be.brahms.rent_server.services.FavorService;
import org.springframework.stereotype.Service;

/**
 * Service implementation for managing favor.
 * Uses FavorRepository to perform database operations.
 */
@Service
public class FavorServiceImpl implements FavorService {

    private final FavorRepository favorRepository;

    /**
     * Constructor to create BillServiceImpl with BillRepository.
     *
     * @param favorRepository the repository to access favor data
     */
    public FavorServiceImpl(FavorRepository favorRepository) {
        this.favorRepository = favorRepository;
    }
}
