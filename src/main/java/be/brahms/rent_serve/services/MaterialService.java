package be.brahms.rent_serve.services;

import be.brahms.rent_serve.models.entities.Material;

import java.util.List;

/**
 * Service interface for managing material.
 * Defines business operations related to Bill entities.
 */
public interface MaterialService {

    /**
     * This method get a list of materials
     *
     * @return list of all material
     */
    List<Material> findAllMaterials();

    /**
     * This method get material
     *
     * @param id The identifier of maerial
     * @return the name with data from material
     */
    Material findById(long id);

    /**
     * This method create a new material
     *
     * @param material the material
     * @return a save material on DB
     */
    Material create(Material material);
}
