package be.brahms.rent_server.models.forms;

import be.brahms.rent_server.models.entities.User;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

/**
 * Record UserForm into a User entity
 *
 * @param name      the user’s name
 * @param firstName the user’s firstname
 * @param birthdate the user’s birthdate
 * @param pseudo    the chosen nickname
 * @param email     the email address
 * @param password  password
 * @param street    the street
 * @param city      the city
 * @param zipCode   postal code
 */
public record UserForm(
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
        String password,
        @NotBlank
        String street,
        @NotBlank
        String city,
        @NotBlank
        String zipCode
) {
    /**
     * Converts this UserForm into a User entity
     *
     * @return a new instance initialized with data from this form
     */
    public User toEntity() {
        return new User(this.name, this.firstName, this.birthdate, this.pseudo, this.email, this.password, this.street, this.city, this.zipCode);
    }
}
