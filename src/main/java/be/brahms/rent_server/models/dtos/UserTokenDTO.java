package be.brahms.rent_server.models.dtos;

import be.brahms.rent_server.enums.Role;
import be.brahms.rent_server.models.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * This class holds user data and a token.
 * It is used to send user info with a token.
 */
@Getter
@Setter
@AllArgsConstructor
public class UserTokenDTO {
    private String name;
    private String firstName;
    private LocalDate birthdate;
    private String email;
    private Role role;
    private Boolean isActive;
    private String token;

    /**
     * Constructor by default
     */
    public UserTokenDTO() {

    }

    /**
     * This method makes a UserTokenDTO from a User.
     * It copies name, first name, birthdate, email, role, and active status.
     *
     * @param user the user to convert
     * @return the new UserTokenDTO
     */
    public static UserTokenDTO fromEntity(User user) {
        UserTokenDTO userToknDto = new UserTokenDTO();
        userToknDto.setName(user.getName());
        userToknDto.setFirstName(user.getFirstName());
        userToknDto.setBirthdate(user.getBirthdate());
        userToknDto.setEmail(user.getEmail());
        userToknDto.setRole(user.getRole());
        userToknDto.setIsActive(user.getIsActive());
        return userToknDto;
    }

}