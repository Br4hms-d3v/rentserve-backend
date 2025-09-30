package be.brahms.rent_serve.models.dtos.userFavor;

import be.brahms.rent_serve.exceptions.userFavor.UserFavourEmptyException;
import be.brahms.rent_serve.models.dtos.favor.FavorDto;
import be.brahms.rent_serve.models.entities.UserFavor;

import java.util.List;

/**
 * Data Transfer Object for a UserFavor grouped by Favor
 *
 * @param favor      The favor from DTO
 * @param userFavour The user favor from DTO
 */
public record UserFavourGroupedByFavourDto(
        FavorDto favor,
        List<UserFavorDto> userFavour
) {
    /**
     * Creates a {@code UserFavourGroupedByFavourDto} object from a list of {@code UserFavor} entities.
     * This method groups user favors by their associated favor and transforms them into DTOs.
     *
     * @param userFavour the list of {@code UserFavor} entities to group and transform. Must not be null or empty.
     * @return a {@code UserFavourGroupedByFavourDto} containing the grouped favor information and user favors DTOs.
     * @throws IllegalArgumentException if the provided list is null or empty.
     */
    public static UserFavourGroupedByFavourDto fromGroup(List<UserFavor> userFavour) {
        // Check if the userFavour list is null or empty
        if (userFavour == null || userFavour.isEmpty()) {
            throw new UserFavourEmptyException(); // Throw an exception if a list is empty or null
        }

        // Get the favor info from the first UserFavor in the list and convert it to a DTO
        FavorDto favorDto = FavorDto.fromEntity(userFavour.getFirst().getFavor());

        // Convert all UserFavor objects in the list to UserFavorDto objects
        List<UserFavorDto> userFavorDtoList = userFavour // Start processing the list of userFavors to convert each into a UserFavorDto
                .stream()// go through the list one by one
                .map(UserFavorDto::fromEntity) // Change each UserFavor to UserFavorDto
                .toList(); // Collect them all into a list

        // Create a UserFavourGroupedByFavourDto with the favor info and list of user favor DTOs
        return new UserFavourGroupedByFavourDto(favorDto, userFavorDtoList);

    }
}
