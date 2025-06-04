package be.brahms.rent_server.services.impl;

import be.brahms.rent_server.repositories.UserRepository;
import be.brahms.rent_server.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
