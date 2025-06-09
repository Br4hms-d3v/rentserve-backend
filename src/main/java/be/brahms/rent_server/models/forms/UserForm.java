package be.brahms.rent_server.models.forms;

import be.brahms.rent_server.models.entities.User;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record UserForm(
        @NotNull
        @NotBlank
        String name,
        @NotNull
        @NotBlank
        String firstName,
        @NotNull
        LocalDate birthdate,
        @NotNull
        @NotBlank
        @Size(min = 3, max = 150)
        String pseudo,
        @Email
        @NotBlank
        String email,
        @NotNull
        @NotBlank
        String password,
        @NotNull
        @NotBlank
        String street,
        @NotNull
        @NotBlank
        String city,
        @NotNull
        @NotBlank
        String zipCode
) {
    public User toEntity() {
        return new User(this.name, this.firstName, this.birthdate, this.pseudo, this.email, this.password, this.street, this.city, this.zipCode);
    }
}
