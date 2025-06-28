package be.brahms.rent_serve.exceptions.global;

import be.brahms.rent_serve.exceptions.user.*;
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
     * Default constructor for GlobalExceptionHandler.
     * <p>
     * This constructor is used to create an instance of the exception handler.
     * </p>
     */
    public GlobalExceptionHandler() {
    }

    /**
     * Handles AccountNotActivatedException.
     *
     * @param except The exception that was thrown.
     * @return A response with a forbidden status and the error message.
     */
    @ExceptionHandler(AccountNotActivatedException.class)
    public ResponseEntity<String> handleAccountNotActivatedException(AccountNotActivatedException except) {
        return new ResponseEntity<>(except.getMessage(), HttpStatus.FORBIDDEN);
    }

    /**
     * Handles EmailExistException.
     *
     * @param except The exception that was thrown.
     * @return A response with a found status and the error message.
     */
    @ExceptionHandler(EmailExistException.class)
    public ResponseEntity<String> handleEmailExistException(EmailExistException except) {
        return new ResponseEntity<>(except.getMessage(), HttpStatus.FOUND);
    }

    /**
     * Handles EmailNotFoundException.
     *
     * @param except The exception that was thrown.
     * @return A response with a not found status and the error message.
     */
    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<String> handleNotFoundEmailException(EmailNotFoundException except) {
        return new ResponseEntity<>(except.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles PseudoExistException.
     *
     * @param except The exception that was thrown.
     * @return A response with a found status and the error message.
     */
    @ExceptionHandler(PseudoExistException.class)
    public ResponseEntity<String> handlePseudoExistException(PseudoExistException except) {
        return new ResponseEntity<>(except.getMessage(), HttpStatus.FOUND);
    }

    /**
     * Handles PseudoNotFoundException.
     *
     * @param except The exception that was thrown.
     * @return A response with a not found status and the error message.
     */
    @ExceptionHandler(PseudoNotFoundException.class)
    public ResponseEntity<String> handlePseudoNotFoundException(PseudoNotFoundException except) {
        return new ResponseEntity<>(except.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles UserNotFoundException.
     *
     * @param except The exception that was thrown.
     * @return A response with a not found status and the error message.
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException except) {
        return new ResponseEntity<>(except.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles InvalidPasswordException.
     *
     * @param except The exception that was thrown.
     * @return A response with an unauthorized status and the error message.
     */
    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<String> handleInvalidPasswordException(InvalidPasswordException except) {
        return new ResponseEntity<>(except.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handles any RuntimeException.
     *
     * @param except The exception that was thrown.
     * @return A response with a bad request status and the error message.
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException except) {
        return new ResponseEntity<>(except.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
