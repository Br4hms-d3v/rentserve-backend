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

    @Query("SELECT um FROM UserMaterial um WHERE um.isAvailable = true ")
    List<UserMaterial> findAllMaterialAvailable();

    @Query("SELECT um FROM UserMaterial um WHERE um.isAvailable = false ")
    List<UserMaterial> findAllMaterialNotAvailable();

    @Query("SELECT um FROM UserMaterial  um WHERE um.user.id = :userId AND um.isAvailable = :availableOrNot")
    List<UserMaterial> findAllMaterialByOwner(@Param("userId") long userId, @Param("availableOrNot") boolean availableOrNot);
}
