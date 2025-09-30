package be.brahms.rent_serve.services;

import be.brahms.rent_serve.models.entities.UserFavor;

import java.util.List;

/**
 * Service interface for managing user favor.
 * Defines business operations related to Bill entities.
 */
public interface UserFavorService {

    /**
     * This method gets a list of favour
     *
     * @return list of all favour
     */
    List<UserFavor> findAllUserFavour();

    /**
     * This method gets a list of favour by favor id.
     *
     * @param favorId the id of favor
     * @return a list of favour by favor id
     */
    List<UserFavor> findAllUserFavourById(long favorId);

    /**
     * This method gets a list of favour available
     *
     * @return a list of favour available which is true
     */
    List<UserFavor> findAllUserFavourAvailable();

    /**
     * This method gets a list of favour not available
     *
     * @return a list of favour not available which is false
     */
    List<UserFavor> findAllUserFavourNotAvailable();

    /**
     * This method gets a favor by id.
     *
     * @param id The id of favor
     * @return a detail of favor
     */
    UserFavor findUserFavorById(long id);

    /**
     * This method gets a list of favour by user id.
     *
     * @param userId the id of user
     * @return a list of favour by user id
     */
    List<UserFavor> getUserFavorByUserId(long userId);

    /**
     * This method updates a user favor.
     *
     * @param id        the id of user favor
     * @param userFavor the new data of user favor
     * @return an updated user favor
     */
    UserFavor updateUserFavor(long id, UserFavor userFavor);

    /**
     * This method deletes a user favor.
     *
     * @param id The id of user favor
     */
    void deleteUserFavor(long id);

    /**
     * This method creates a new user favor.
     *
     * @param userFavor The user favor data to create.
     * @return the created user favor.
     */
    UserFavor createUserFavor(UserFavor userFavor);
}
