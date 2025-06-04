package be.brahms.rent_server.services.impl;

import be.brahms.rent_server.repositories.UserFavorRepository;
import be.brahms.rent_server.services.UserFavorService;
import org.springframework.stereotype.Service;

@Service
public class UserFavorServiceImpl implements UserFavorService {

    private final UserFavorRepository userFavorRepository;

    public UserFavorServiceImpl(UserFavorRepository userFavorRepository) {
        this.userFavorRepository = userFavorRepository;
    }
}
