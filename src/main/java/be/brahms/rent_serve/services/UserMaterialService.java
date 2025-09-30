package be.brahms.rent_serve.services;

import be.brahms.rent_serve.models.entities.UserMaterial;

import java.nio.file.AccessDeniedException;
import java.util.List;

/**
 * Service interface for managing user material.
 * Defines business operations related to Bill entities.
 */
public interface UserMaterialService {

    /**
     * Finds all user materials.
     *
     * @return A list of all user materials.
     */
    List<UserMaterial> findAllUserMaterials();

    /**
     * Finds all user materials that are available.
     *
     * @return A list of user materials with availability = true.
     */
    List<UserMaterial> listUserMaterialAvailable();

    /**
     * Finds all user materials that are NOT available.
     *
     * @return A list of user materials with availability = false.
     */
    List<UserMaterial> listUserMaterialNotAvailable();

    /**
     * Finds one user material by its ID.
     *
     * @param id The ID of the user material.
     * @return The user material with this ID.
     */
    UserMaterial findUserMaterialById(long id);

    /**
     * Updates a user material by its ID.
     *
     * @param id           The ID of the user material to update.
     * @param userMaterial The new data for the user material.
     * @return The updated user material.
     * @throws AccessDeniedException If user cannot update.
     */
    UserMaterial updateUserMaterial(long id, UserMaterial userMaterial) throws AccessDeniedException;

    /**
     * Deletes a user material by its ID.
     *
     * @param id The ID of the user material to delete.
     */
    void deleteUserMaterial(long id);

    /**
     * Creates a new user material.
     *
     * @param userMaterial The user material data to create.
     * @return The created user material.
     */
    UserMaterial createUserMaterial(UserMaterial userMaterial);

    /**
     * Gets all user materials of one user by the user's ID.
     *
     * @param userId The ID of the user.
     * @return A list of user materials for this user.
     */
    List<UserMaterial> getUserMaterialsByUserId(long userId);

    List<UserMaterial> findAllUserMaterialsById(long materialId);
}
