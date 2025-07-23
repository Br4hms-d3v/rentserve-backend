package be.brahms.rent_serve.controllers;

import be.brahms.rent_serve.enums.Role;
import be.brahms.rent_serve.hateaos.UserAssembler;
import be.brahms.rent_serve.models.dtos.user.UserDto;
import be.brahms.rent_serve.models.dtos.user.UserRoleDto;
import be.brahms.rent_serve.models.entities.User;
import be.brahms.rent_serve.models.forms.user.UserUpdateForm;
import be.brahms.rent_serve.models.forms.user.UserUpdatePasswordForm;
import be.brahms.rent_serve.services.UserService;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This controller manages users
 * Display the profile user
 * Update the profile from user
 * Delete the account by id
 * List of roles
 * Change only password
 */
@RestController
@RequestMapping("/api/user/")
public class UserController {

    private final UserService userService;
    private final UserAssembler userAssembler;

    /**
     * This is the constructor for UserController.
     *
     * @param userService   This is the service to manage users.
     * @param userAssembler This helps to change user data to link.
     */
    public UserController(UserService userService, UserAssembler userAssembler) {
        this.userService = userService;
        this.userAssembler = userAssembler;
    }

    /**
     * Get all users from the database.
     * <p>
     * This method returns a list of all users.
     * Each user is converted to a UserDto (Data Transfer Object).
     * Each UserDto is wrapped inside an EntityModel with HATEOAS links.
     *
     * @return ResponseEntity with a list of user models
     */
    @GetMapping("list")
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<List<EntityModel<UserDto>>> getUsers() {
        List<User> userList = userService.findAllUsers();
        List<UserDto> userDtoList = userList
                .stream()
                .map(UserDto::fromEntity)
                .toList();

        List<EntityModel<UserDto>> listUserDtoModel = userDtoList
                .stream()
                .map(userAssembler::toModel)
                .toList();

        return ResponseEntity.ok().body(listUserDtoModel);
    }

    /**
     * This method gets a user by their ID.
     *
     * @param id The ID of the user we want to find.
     * @return A response with the user data and link.
     */
    @GetMapping("{id}")
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<EntityModel<UserDto>> getUser(@PathVariable long id) {
        User user = userService.findById(id);
        UserDto userDto = UserDto.fromEntity(user);

        return ResponseEntity.ok().body(userAssembler.toModel(userDto));
    }

    /**
     * Get a list of users by their role.
     *
     * @param role the role to find users
     * @return response with a list of user models
     */
    @GetMapping("list/{role}")
    public ResponseEntity<List<EntityModel<UserRoleDto>>> getUsersByRole(@PathVariable Role role) {

        List<User> userList = userService.findAllUsersByRole(role);
        List<UserRoleDto> userRoleDtoList = userList
                .stream()
                .map(UserRoleDto::fromEntity)
                .toList();

        List<EntityModel<UserRoleDto>> listUserRoleDtoModel = userRoleDtoList
                .stream()
                .map(userAssembler::toModel)
                .toList();

        return ResponseEntity.ok().body(listUserRoleDtoModel);
    }

    /**
     * Update the user with the given ID.
     *
     * <p>This method allows a user with role MEMBER to update their own account.
     * It checks if the user making the request is the same as the user in the form.</p>
     *
     * @param id   the ID of the user to update
     * @param form the new data to update the user (must be valid)
     * @return a response with the updated user if successful,
     * or a bad request response if the user is not allowed
     */
    @PutMapping("{id}/edit")
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<EntityModel<UserDto>> getUserEdit(@PathVariable long id, @RequestBody @Valid UserUpdateForm form) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getName().equals(form.pseudo())) {
            User updateUser = userService.updateUser(id, form.toEntity());
            UserDto userUpdateDto = UserDto.fromEntity(updateUser);

            return ResponseEntity.ok().body(userAssembler.toModel(userUpdateDto));
        }
        return ResponseEntity.badRequest().build();

    }

    /**
     * Change only the password
     * Check if the user know his email before to change the password
     * Check the password if it's the same, after that it save
     *
     * @param id   the user's data
     * @param form the form to update the password only
     * @return the link to update only password
     */
    @PatchMapping("{id}/change-password")
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<EntityModel<UserDto>> getUpdatePassword(@PathVariable long id, @RequestBody @Valid UserUpdatePasswordForm form) {

        User user = userService.findById(id);

        if (!user.getEmail().equals(form.email())) {
            throw new RuntimeException("Veuillez vérifiez votre adresse e-mail");
        }

        if (!form.password().equals(form.comparePassword())) {
            throw new RuntimeException("Le mot de passe doit être identique");
        }

        User userPassword = userService.updatePassword(id, form.toEntity());
        UserDto userUpdatePasswordDto = UserDto.fromEntity(userPassword);

        return ResponseEntity.ok().body(userAssembler.toModel(userUpdatePasswordDto));
    }

    /**
     * Delete the user.
     * But change the boolean not data
     * From true to false
     *
     * @param id the user's ID
     * @return the delete the user with links
     */
    @DeleteMapping("{id}/delete")
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<EntityModel<UserDto>> deleteUser(@PathVariable long id) {
        User deleteUser = userService.deleteUser(id);
        UserDto userDeleteDto = UserDto.fromEntity(deleteUser);
        return ResponseEntity.ok().body(userAssembler.toModel(userDeleteDto));
    }
}
