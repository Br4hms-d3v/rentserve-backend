package be.brahms.rent_serve.services;

import be.brahms.rent_serve.models.entities.UserFavor;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Service interface for managing user favor.
 * Defines business operations related to Bill entities.
 */
public interface UserFavorService {

    List<UserFavor> findAllUserFavour();

    List<UserFavor> findAllUserFavourById(@Param("favorId") long favorId);

    List<UserFavor> findAllUserFavourAvailable();

    List<UserFavor> findAllUserFavourNotAvailable();

    UserFavor findUserFavorById(long id);
}
