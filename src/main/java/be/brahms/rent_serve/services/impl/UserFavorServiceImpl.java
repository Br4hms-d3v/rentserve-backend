package be.brahms.rent_serve.services.impl;

import be.brahms.rent_serve.exceptions.userFavor.UserFavourEmptyException;
import be.brahms.rent_serve.models.entities.UserFavor;
import be.brahms.rent_serve.repositories.UserFavorRepository;
import be.brahms.rent_serve.services.UserFavorService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation for managing user favor.
 * Uses UserFavorRepository to perform database operations.
 */
@Service
public class UserFavorServiceImpl implements UserFavorService {

    private final UserFavorRepository userFavorRepository;

    /**
     * Constructor to create UserFavorServiceImpl with UserFavorRepository.
     *
     * @param userFavorRepository the repository to access user favor data
     */
    public UserFavorServiceImpl(UserFavorRepository userFavorRepository) {
        this.userFavorRepository = userFavorRepository;
    }

    @Override
    public List<UserFavor> findAllUserFavour() {
        List<UserFavor> userFavors = userFavorRepository.findAll();

        if (userFavors.isEmpty()) {
            throw new UserFavourEmptyException();
        }
        return userFavors;
    }
}
