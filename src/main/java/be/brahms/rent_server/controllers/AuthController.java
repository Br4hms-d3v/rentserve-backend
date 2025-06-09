package be.brahms.rent_server.controllers;

import be.brahms.rent_server.models.dtos.UserTokenDTO;
import be.brahms.rent_server.models.entities.User;
import be.brahms.rent_server.models.forms.UserForm;
import be.brahms.rent_server.services.UserService;
import be.brahms.rent_server.utilities.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller manages authentication.
 * It has method to register a new user.
 */
@RestController
@RequestMapping(name = "/api/auth/")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    /**
     * This method registers a new user.
     * It makes a token and sends back user info with the token.
     *
     * @param form the form with user data
     * @return user info and token
     */
    @PostMapping("registration")
    public ResponseEntity<UserTokenDTO> registre(@RequestBody @Valid UserForm form) {
        User registerUser = userService.register(form.toEntity());
        String token = jwtUtil.generateToken(registerUser);
        UserTokenDTO userTkDto = UserTokenDTO.fromEntity(registerUser);
        userTkDto.setToken(token);
        return ResponseEntity.ok().body(userTkDto);
    }
}
