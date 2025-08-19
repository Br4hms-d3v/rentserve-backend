package be.brahms.rent_serve.models.dtos.favor;

import be.brahms.rent_serve.models.entities.Favor;

/**
 * A dto (Data Transfer Object) for favor information
 * It contains simple data about the favor (name of service)
 *
 * @param id        The unique identifier
 * @param nameFavor The name of service
 */
public record FavorDto(
        long id,
        String nameFavor
) {
    /**
     * Create a FavorDto from a service
     *
     * @param favor the main favor object
     * @return a new FavorDto with data form object (service)
     */
    public static FavorDto fromEntity(Favor favor) {
        return new FavorDto(
                favor.getId(),
                favor.getNameFavor()
        );
    }
}
