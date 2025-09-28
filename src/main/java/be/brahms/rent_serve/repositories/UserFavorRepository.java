package be.brahms.rent_serve.repositories;

import be.brahms.rent_serve.models.entities.User;
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

    @Query("SELECT uf FROM UserFavor uf WHERE uf.favor.id = :favorId AND uf.isAvailable = true")
    List<UserFavor> findByFavorId(@Param("favorId") long favorId);

    @Query("SELECT uf FROM UserFavor uf WHERE uf.isAvailable = true")
    List<UserFavor> findAllUserFavorAvailable();

    @Query("SELECT uf FROM UserFavor uf WHERE uf.isAvailable = false")
    List<UserFavor> findAllUserFavorNotAvailable();

    List<UserFavor> findByUserId(long userId);

    long user(User user);
}
