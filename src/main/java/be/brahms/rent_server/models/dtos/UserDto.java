package be.brahms.rent_server.models.dtos;

import be.brahms.rent_server.models.entities.User;

import java.time.LocalDate;

/**
 * UserDto is a data transfer object for user information.
 * It holds user details like name, first name, birthdate, pseudo, email, street, city, and zip code.
 *
 * @param id        the identification of the user
 * @param name      the name of user
 * @param firstName the firstname of user
 * @param birthdate the birthdate of user
 * @param pseudo    the nickname of user
 * @param email     the e-mail of user
 * @param street    the number and name of street where live the user
 * @param city      the name of city
 * @param zipCode   the number of city
 * @param isActive  the boolean for user activate his account
 */
public record UserDto(
        Long id,
        String name,
        String firstName,
        LocalDate birthdate,
        String pseudo,
        String email,
        String street,
        String city,
        String zipCode,
        boolean isActive
) {
    /**
     * Create a UserDto from a User entity.
     *
     * @param user The User entity to convert.
     * @return A new UserDto with user information.
     */
    public static UserDto fromEntity(User user) {
        return new UserDto(user.getId(), user.getName(), user.getFirstName(), user.getBirthdate(), user.getPseudo(), user.getEmail(), user.getStreet(), user.getCity(), user.getZipCode(), user.getIsActive());
    }
}
