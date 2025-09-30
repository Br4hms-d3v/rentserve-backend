package be.brahms.rent_serve.models.dtos.userFavor;

import be.brahms.rent_serve.models.entities.Picture;
import be.brahms.rent_serve.models.entities.UserFavor;

import java.math.BigDecimal;

/**
 * Data Transfer Object for a UserFavor
 * This class is used to transfer data between the client and the server.
 *
 * @param id             the unique identifier
 * @param priceHourFavor the price to rent per hour
 * @param isAvailable    the availability of favor
 * @param picture        the first picture of favor
 */
public record UserFavorDto(
        Long id,
        BigDecimal priceHourFavor,
        Boolean isAvailable,
        String picture
) {
    /**
     * Convert a UserFavor entity to a UserFavorDto
     *
     * @param userFavor the entity to convert
     * @return the converted UserFavorDto
     */
    public static UserFavorDto fromEntity(UserFavor userFavor) {
        String firstPicture = userFavor.getPictures()
                .stream()
                .map(Picture::getNamePicture)
                .findFirst()
                .orElse("Aucune photo trouvée");

        return new UserFavorDto(
                userFavor.getId(),
                userFavor.getPriceHourFavor(),
                userFavor.isAvailable(),
                firstPicture
        );
    }
}
