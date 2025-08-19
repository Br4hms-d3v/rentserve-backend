package be.brahms.rent_serve.services;

import be.brahms.rent_serve.models.entities.Favor;

import java.util.List;

/**
 * Service interface for managing favor.
 * Defines business operations related to Bill entities.
 */
public interface FavorService {

    List<Favor> findAllFavour();
}
