package be.brahms.rent_serve.exceptions.global;

import be.brahms.rent_serve.exceptions.user.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for the application.
 * This class catches exceptions thrown by controllers and handles them.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles all exceptions of type UserException.
     * When a UserException is thrown, this method returns a response with
     * the error message and a 404 Not Found HTTP status.
     *
     * @param excpt The UserException that was thrown
     * @return ResponseEntity with the error message and HTTP status 404
     */
    @ExceptionHandler(UserException.class)
    public ResponseEntity<String> handleUserException(UserException excpt) {
        return new ResponseEntity<>(excpt.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException excpt) {
        return new ResponseEntity<>(excpt.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }
}
