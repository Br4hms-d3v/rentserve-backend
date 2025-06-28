package be.brahms.rent_serve.models.forms.user;

import be.brahms.rent_serve.models.entities.User;
import jakarta.validation.constraints.*;

/**
 * Form used to update only password
 *
 * @param email           The email
 * @param password        The password
 * @param comparePassword check the password is identitque
 */
public record UserUpdatePasswordForm(

        @Email
        @NotBlank
        String email,
        @NotBlank
        String password,
        @NotBlank
        String comparePassword
) {
    /**
     * Converts this UserUpdateForm into a User entity
     *
     * @return a new instance initialized with data from this form
     */
    public User toEntity() {
        return new User(this.email, this.password);
    }
}
