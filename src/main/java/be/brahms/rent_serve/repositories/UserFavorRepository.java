package be.brahms.rent_serve.repositories;

import be.brahms.rent_serve.models.entities.UserFavor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for managing UserFavor entities.
 * Provides basic CRUD operations and more using JpaRepository.
 */
@Repository
public interface UserFavorRepository extends JpaRepository<UserFavor, Long> {

    /**
     * Find all user favor by favor id.
     *
     * @param favorId The id of favor
     * @return a list of user favor
     */
    @Query("SELECT uf FROM UserFavor uf WHERE uf.favor.id = :favorId AND uf.isAvailable = true")
    List<UserFavor> findByFavorId(@Param("favorId") long favorId);

    /**
     * Find all user favour that's available.
     *
     * @return a list of user favor which is available
     */
    @Query("SELECT uf FROM UserFavor uf WHERE uf.isAvailable = true")
    List<UserFavor> findAllUserFavorAvailable();

    /**
     * Find all user favour that's not available.
     *
     * @return a list of user favor which is not available
     */
    @Query("SELECT uf FROM UserFavor uf WHERE uf.isAvailable = false")
    List<UserFavor> findAllUserFavorNotAvailable();

    /**
     * Find all user favour by user id.
     *
     * @param userId The id of user
     * @return a list of user favor
     */
    List<UserFavor> findByUserId(long userId);

}
