package be.brahms.rent_serve.models.dtos.userFavor;

import be.brahms.rent_serve.models.dtos.favor.FavorDto;
import be.brahms.rent_serve.models.dtos.user.UserDto;
import be.brahms.rent_serve.models.entities.Picture;
import be.brahms.rent_serve.models.entities.UserFavor;

import java.math.BigDecimal;
import java.util.List;

/**
 * Data Transfer Object for a UserFavor by Id
 *
 * @param id               The unique identifier
 * @param descriptionFavor The text to describe the favor
 * @param priceHourFavor   The price to rent per hour
 * @param isAvailable      The favor is available
 * @param pictures         The string for picture
 * @param user             The user
 * @param favor            The favor
 */
public record UserFavorByIdDto(
        long id,
        String descriptionFavor,
        BigDecimal priceHourFavor,
        Boolean isAvailable,
        List<String> pictures,
        UserDto user,
        FavorDto favor
) {
    /**
     * Make a UserFavorByIdDto from a UserFavor object.
     *
     * @param userFavor The UserFavor object with data.
     * @return a new UserFavorByIdDto with selected information.
     */
    public static UserFavorByIdDto fromEntity(UserFavor userFavor) {
        // Get a list of picture names from userFavor
        List<String> allpictures = userFavor.getPictures()
                .stream()
                .map(Picture::getNamePicture)
                .toList();

        // Create and return UserFavorByIdDto with values from userFavor and pictures
        return new UserFavorByIdDto(
                userFavor.getId(),
                userFavor.getDescriptionFavor(),
                userFavor.getPriceHourFavor(),
                userFavor.isAvailable(),
                allpictures,
                UserDto.fromEntity(userFavor.getUser()), // Convert user information to UserDto
                FavorDto.fromEntity(userFavor.getFavor()) // Convert favor information to FavorDto
        );
    }
}
