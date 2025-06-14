package be.brahms.rent_serve.repositories;

import be.brahms.rent_serve.models.entities.UserMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for managing UserMaterial entities.
 * Provides basic CRUD operations and more using JpaRepository.
 */
@Repository
public interface UserMaterialRepository extends JpaRepository<UserMaterial, Long> {
}
