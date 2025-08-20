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

    /**
     * This method create a new Service
     *
     * @param favor The favor
     * @return a new favor saved on DB
     */
    Favor create(Favor favor);

    /**
     * This method update the favor
     *
     * @param id    The identifier
     * @param favor The material contain name of favor and his category
     * @return a name of favor updated or change the category
     */
    Favor updateFavor(long id, Favor favor);

    /**
     * This method delete the favor
     *
     * @param id The identifier
     * @return a success message after delete favor
     */
    Favor deleteFavor(long id);

    /**
     * This method display list of favour by category
     *
     * @param categoryName the name of category
     * @return a list favour
     */
    List<Favor> findAllFavourByCategoryName(String categoryName);
}
