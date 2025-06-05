package be.brahms.rent_server.repositories;

import be.brahms.rent_server.models.entities.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for managing Picture entities.
 * Provides basic CRUD operations and more using JpaRepository.
 */
@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {
}
