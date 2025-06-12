package be.brahms.rent_server.controllers;

import be.brahms.rent_server.hateaos.UserAssembler;
import be.brahms.rent_server.models.dtos.UserDto;
import be.brahms.rent_server.models.entities.User;
import be.brahms.rent_server.services.UserService;
import be.brahms.rent_server.utilities.JwtUtil;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


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
    private final JwtUtil jwtUtil;
    private final UserAssembler userAssembler;

    /**
     * This is the constructor for UserController.
     *
     * @param userService   This is the service to manage users.
     * @param jwtUtil       This is a tool to work with JWT tokens.
     * @param userAssembler This helps to change user data to link.
     */
    public UserController(UserService userService, JwtUtil jwtUtil, UserAssembler userAssembler) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.userAssembler = userAssembler;
    }

    /**
     * This method gets a user by their ID.
     *
     * @param id The ID of the user we want to find.
     * @return A response with the user data and link.
     */
    @GetMapping("{id}")
    public ResponseEntity<EntityModel<UserDto>> getUser(@PathVariable long id) {
        User user = userService.findById(id);
        UserDto userDto = UserDto.fromEntity(user);

        return ResponseEntity.ok().body(userAssembler.toModel(userDto));
    }

    /**
     * Get all users from the database.
     * <p>
     * This method returns a list of all users.
     * Each user is converted to a UserDto (Data Transfer Object).
     * Each UserDto is wrapped inside an EntityModel with HATEOAS links.
     *
     * @return ResponseEntity with a list of EntityModel<UserDto>
     */
    @GetMapping("list")
    public ResponseEntity<List<EntityModel<UserDto>>> getUsers() {
        List<User> userList = userService.findAllUsers();
        List<UserDto> userDtoList = userList
                .stream()
                .map(UserDto::fromEntity)
                .toList();

        List<EntityModel<UserDto>> listUserDtoModel = userDtoList.stream()
                .map(userAssembler::toModel).toList();

        return ResponseEntity.ok().body(listUserDtoModel);
    }
}
