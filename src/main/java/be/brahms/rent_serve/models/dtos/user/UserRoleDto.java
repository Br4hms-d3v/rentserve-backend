package be.brahms.rent_serve.models.dtos.user;

import be.brahms.rent_serve.enums.Role;
import be.brahms.rent_serve.models.entities.User;

import java.time.LocalDate;

/**
 * UserRoleDto is a data transfer object for role from user
 * It holds user details like name, firstname, role and created
 *
 * @param name      the name of user
 * @param firstName the firstname of user
 * @param role      the role of user
 * @param createdAt the date when user created
 */
public record UserRoleDto(
        String name,
        String firstName,
        Role role,
        LocalDate createdAt
) {
    /**
     * Create a UserRoleDto from a User entity.
     *
     * @param user The User entity to convert.
     * @return A new UserRoleDto with user information.
     */
    public static UserRoleDto fromEntity(User user) {
        return new UserRoleDto(user.getName(), user.getFirstName(), user.getRole(), user.getCreatedAt());
    }
}
