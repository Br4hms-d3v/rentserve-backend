package be.brahms.rent_server.services.impl;

import be.brahms.rent_server.repositories.UserMaterialRepository;
import be.brahms.rent_server.services.UserMaterialService;
import org.springframework.stereotype.Service;

@Service
public class UserMaterialServiceImpl implements UserMaterialService {

    private final UserMaterialRepository userMaterialRepository;

    public UserMaterialServiceImpl(UserMaterialRepository userMaterialRepository) {
        this.userMaterialRepository = userMaterialRepository;
    }
}
