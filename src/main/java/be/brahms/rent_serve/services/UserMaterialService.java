package be.brahms.rent_serve.services;

import be.brahms.rent_serve.models.entities.UserMaterial;

import java.util.List;

/**
 * Service interface for managing user material.
 * Defines business operations related to Bill entities.
 */
public interface UserMaterialService {

    List<UserMaterial> findAllUserMaterials();


}
