package be.brahms.rent_serve.controllers;

import be.brahms.rent_serve.models.dtos.user.UserTokenDTO;
import be.brahms.rent_serve.models.entities.User;
import be.brahms.rent_serve.models.forms.user.UserForm;
import be.brahms.rent_serve.models.forms.user.UserLoginForm;
import be.brahms.rent_serve.services.UserService;
import be.brahms.rent_serve.utilities.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This controller manages authentication.
 * It has a method to register a new user.
 */
@RestController
@RequestMapping("/api/auth/")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    /**
     * AuthController constructor.
     *
     * @param userService service for user management
     * @param jwtUtil     utility for managing JWT tokens
     */
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
    public ResponseEntity<UserTokenDTO> register(@RequestBody @Valid UserForm form) {
        User registerUser = userService.register(form.toEntity());
        String token = jwtUtil.generateToken(registerUser);
        UserTokenDTO userTkDto = UserTokenDTO.fromEntity(registerUser);
        userTkDto.setToken(token);
        return ResponseEntity.ok().body(userTkDto);
    }

    /**
     * This method connects a user.
     * It makes a token and sends back user info with the token.
     *
     * @param form the form with user data
     * @return user connected with his token
     */
    @PostMapping("login")
    public ResponseEntity<UserTokenDTO> login(@RequestBody @Valid UserLoginForm form) {
        User userLogin = userService.login(form.toEntity());
        String token = jwtUtil.generateToken(userLogin);
        UserTokenDTO userTkDto = UserTokenDTO.fromEntity(userLogin);
        userTkDto.setToken(token);

        return ResponseEntity.ok(userTkDto);
    }
}
