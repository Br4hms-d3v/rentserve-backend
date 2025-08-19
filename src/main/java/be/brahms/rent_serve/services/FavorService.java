package be.brahms.rent_serve.services;

import be.brahms.rent_serve.models.entities.Favor;

import java.util.List;

/**
 * Service interface for managing favor.
 * Defines business operations related to Bill entities.
 */
public interface FavorService {

    /**
     * This method get a list of favour
     *
     * @return list of all favour
     */
    List<Favor> findAllFavour();

    /**
     * This method get favor
     *
     * @param id The identifier unique
     * @return the name of favor with somme information
     */
    Favor findFavorById(long id);
}
