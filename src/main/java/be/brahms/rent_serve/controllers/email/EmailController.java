package be.brahms.rent_serve.controllers.email;

import be.brahms.rent_serve.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This controller manages email.
 * It has method to send an email.
 */
@RestController
@RequestMapping("/api/mail")
public class EmailController {

    private final UserService userService;

    /**
     * Constructor for email
     *
     * @param userService the user service
     */
    public EmailController(UserService userService) {
        this.userService = userService;
    }

    /**
     * receive a request to activate the account
     *
     * @param email the user e-mail
     * @return a message on webpage the account is activated
     */
    @GetMapping("/confirmation")
    public ResponseEntity<String> mailConfirmation(@RequestParam String email) {
        userService.activateUser(email);

        return ResponseEntity.ok("Account activated successfully");
    }
}
