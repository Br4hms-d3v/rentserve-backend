package be.brahms.rent_serve.models.dtos.userMaterial;

import be.brahms.rent_serve.models.dtos.material.MaterialDto;
import be.brahms.rent_serve.models.entities.UserMaterial;

import java.util.List;

/**
 * Data Transfer Object for a UserMaterial grouped by Material
 *
 * @param material      The material from DTO
 * @param userMaterials The user material from DTO
 */
public record UserMaterialGroupByMaterialDto(
        MaterialDto material,
        List<UserMaterialDto> userMaterials
) {
    /**
     * Creates a UserMaterialGroupByMaterialDto from a list of user materials.
     * The list must have at least one user material.
     *
     * @param userMaterials A list of user materials to group.
     * @return A new UserMaterialGroupByMaterialDto with the material and its user materials.
     * @throws IllegalArgumentException If the list is empty or null.
     */
    public static UserMaterialGroupByMaterialDto fromGroup(List<UserMaterial> userMaterials) {
        // Check if userMaterials list is null or empty
        if (userMaterials == null || userMaterials.isEmpty()) {
            // If empty, stop and show error message
            throw new IllegalArgumentException("userMaterials list is empty");
        }

        // Get the material info from the first UserMaterial in the list and convert it to a DTO

        MaterialDto materialDto = MaterialDto.fromEntity(userMaterials.getFirst().getMaterial());

        // Convert all UserMaterial objects in the list to UserMaterialDto objects
        List<UserMaterialDto> userMaterialsDto = userMaterials.stream() // Start processing the list of userMaterials to convert each into a UserMaterialDto
                .map(UserMaterialDto::fromEntity) // Change each UserMaterial to UserMaterialDto
                .toList(); // Collect them all into a list

        // Create a UserMaterialGroupByMaterialDto with the material info and list of user materials DTOs
        return new UserMaterialGroupByMaterialDto(materialDto, userMaterialsDto);
    }
}
