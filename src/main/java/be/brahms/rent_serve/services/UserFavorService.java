package be.brahms.rent_serve.services;

import be.brahms.rent_serve.models.entities.UserFavor;

import java.util.List;

/**
 * Service interface for managing user favor.
 * Defines business operations related to Bill entities.
 */
public interface UserFavorService {

    List<UserFavor> findAllUserFavour();
}
