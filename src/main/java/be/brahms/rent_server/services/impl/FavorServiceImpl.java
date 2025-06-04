package be.brahms.rent_server.services.impl;

import be.brahms.rent_server.repositories.FavorRepository;
import be.brahms.rent_server.services.FavorService;
import org.springframework.stereotype.Service;

@Service
public class FavorServiceImpl implements FavorService {

    private final FavorRepository favorRepository;

    public FavorServiceImpl(FavorRepository favorRepository) {
        this.favorRepository = favorRepository;
    }
}
