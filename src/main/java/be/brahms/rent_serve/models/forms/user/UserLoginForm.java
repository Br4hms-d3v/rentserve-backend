package be.brahms.rent_serve.models.forms.user;

import be.brahms.rent_serve.models.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * This form is used when a user wants to log in.
 * It takes the email or pseudo and the password.
 *
 * @param email    the user's email (must be valid)
 * @param pseudo   the user's pseudo (optional if email is used)
 * @param password the user's password (cannot be blank)
 */
public record UserLoginForm(
        @Email
        String email,
        String pseudo,
        @NotBlank
        String password
) {
    /**
     * Change this form to a User object.
     *
     * @return a new User with email, pseudo, and password
     */
    public User toEntity() {
        return new User(this.email, this.pseudo, this.password);
    }
}
