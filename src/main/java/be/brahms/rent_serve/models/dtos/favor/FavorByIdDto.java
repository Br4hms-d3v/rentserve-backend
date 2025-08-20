package be.brahms.rent_serve.models.dtos.favor;

import be.brahms.rent_serve.models.entities.Favor;

/**
 * A dto (Data Transfer Object) for a favor information.
 * It contains simple data about the favor and his details
 *
 * @param id          The unique identifier
 * @param nameFavor   The name of service
 * @param isAvailable The favor is available
 * @param category    The name of category
 */
public record FavorByIdDto(
        long id,
        String nameFavor,
        boolean isAvailable,
        String category
) {
    /**
     * Create a FavorDto form one favor
     *
     * @param favor The main favor object
     * @return details from favor with some data
     */
    public static FavorByIdDto fromEntity(Favor favor) {
        return new FavorByIdDto(
                favor.getId(),
                favor.getNameFavor(),
                favor.isAvailable(),
                favor.getCategory().getNameCategory()
        );
    }
}
