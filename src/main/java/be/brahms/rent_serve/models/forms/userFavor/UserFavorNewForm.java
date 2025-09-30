package be.brahms.rent_serve.models.forms.userFavor;

import be.brahms.rent_serve.models.entities.Favor;
import be.brahms.rent_serve.models.entities.Picture;
import be.brahms.rent_serve.models.entities.UserFavor;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A form for creating a new UserFavor
 *
 * @param descriptionFavor The text to describe the favor
 * @param priceHourFavor   The price per hour
 * @param isAvailable      The availability of favor
 * @param favorId          The ID of favor
 * @param pictureNames     The string of picture
 */
public record UserFavorNewForm(
        @NotBlank
        String descriptionFavor,
        @Digits(integer = 4, fraction = 2) // 4 numbers before the dot and 2 after the dot
        @DecimalMin(value = "5.0", inclusive = false) // The value can be start 5.0 or more but never under
        BigDecimal priceHourFavor,
        @NotNull
        Boolean isAvailable,
        @NotNull
        Long favorId,
        List<String> pictureNames
) {
    /**
     * Convert this form into a UserFavor entity
     *
     * @return a new UserFavor with the data from this form
     */
    public UserFavor toEntity() {
        UserFavor newUserFavor = new UserFavor();

        newUserFavor.setDescriptionFavor(descriptionFavor);
        newUserFavor.setPriceHourFavor(priceHourFavor);
        newUserFavor.setAvailable(isAvailable);

        // If favorId is not null, create a Favor and set its ID
        if (this.favorId != null) {
            Favor favor = new Favor();
            favor.setId(this.favorId); // Set the ID of the favor to the value from this form
            newUserFavor.setFavor(favor); // Push the favor to the userFavor
        }

        // If there are picture names, create Picture objects and add them
        if (this.pictureNames != null && !this.pictureNames.isEmpty()) {
            // Go through each picture name to create a set of Picture objects
            Set<Picture> pictures = pictureNames
                    .stream()
                    .map(name -> {
                        Picture picture = new Picture(); // Create a new Picture
                        picture.setNamePicture(name); // Set a name of this picture
                        return picture; // Return the picture
                    })
                    .collect(Collectors.toSet()); // Collect all pictures in a set

            newUserFavor.setPictures(pictures); // Add pictures to UserFavor
        }

        // Return the final UserFavor object
        return newUserFavor;

    }
}
