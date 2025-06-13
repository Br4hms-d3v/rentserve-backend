package be.brahms.rent_server.models.forms;

import be.brahms.rent_server.models.entities.User;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

/**
 * Form used to update a user's information
 *
 * @param name      the user’s name (not blank)
 * @param firstName the user’s firstname (not blank)
 * @param birthdate the user’s birthdate
 * @param pseudo    the user's pseudo (optional if email is used)
 * @param email     Convert to Markdown documentation comment
 * @param street    the street
 * @param city      the city
 * @param zipCode   postal code
 */
public record UserUpdateForm(
        @NotBlank
        String name,
        @NotBlank
        String firstName,
        @Past
        @NotNull
        LocalDate birthdate,
        @NotBlank
        @Size(min = 3, max = 150)
        String pseudo,
        @Email
        @NotBlank
        String email,
        @NotBlank
        String street,
        @NotBlank
        String city,
        @NotBlank
        String zipCode
) {
    /**
     * Converts this UserUpdateForm into a User entity
     *
     * @return a new instance initialized with data from this form
     */
    public User toEntity() {
        return new User(this.name, this.firstName, this.birthdate, this.pseudo, this.email, this.street, this.city, this.zipCode);
    }
}
