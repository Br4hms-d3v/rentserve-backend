package be.brahms.rent_serve.repositories;

import be.brahms.rent_serve.models.entities.UserMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for managing UserMaterial entities.
 * Provides basic CRUD operations and more using JpaRepository.
 */
@Repository
public interface UserMaterialRepository extends JpaRepository<UserMaterial, Long> {

    /**
     * Finds all user materials that are available.
     *
     * @return A list of user materials with availability set to true.
     */
    @Query("SELECT um FROM UserMaterial um WHERE um.isAvailable = true ")
    List<UserMaterial> findAllMaterialAvailable();

    /**
     * Finds all user materials that are NOT available.
     *
     * @return A list of user materials with availability set to false.
     */
    @Query("SELECT um FROM UserMaterial um WHERE um.isAvailable = false ")
    List<UserMaterial> findAllMaterialNotAvailable();

//    @Query("SELECT um FROM UserMaterial  um WHERE um.user.id = :userId AND um.isAvailable = :availableOrNot")
//    List<UserMaterial> findAllMaterialByOwner(@Param("userId") long userId, @Param("availableOrNot") boolean availableOrNot);

    /**
     * Finds all user materials for a specific user by their ID.
     *
     * @param userId The ID of the user.
     * @return A list of user materials that belong to the user.
     */
    List<UserMaterial> findByUserId(long userId);
}
