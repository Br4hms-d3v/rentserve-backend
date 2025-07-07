package be.brahms.rent_serve.exceptions.global;

import be.brahms.rent_serve.exceptions.dtos.ApiError;
import be.brahms.rent_serve.exceptions.user.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.View;

/**
 * Global exception handler for the application.
 * This class catches exceptions thrown by controllers and handles them.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final View error;

    /**
     * Default constructor for GlobalExceptionHandler.
     * <p>
     * This constructor is used to create an instance of the exception handler.
     * </p>
     */
    public GlobalExceptionHandler(View error) {
        this.error = error;
    }

    /**
     * Handles AccountNotActivatedException and sends a 403 Forbidden error.
     * <p>
     * This method is called automatically when the user doesn't activate the account
     * It creates an ApiError and sends it to the frontend.
     *
     * @param except The exception that was thrown (AccountNotActivatedException).
     * @return A response with an apiError and HTTP status 403 (FORBIDDEN).
     */
    @ExceptionHandler(AccountNotActivatedException.class)
    public ResponseEntity<ApiError> handleAccountNotActivatedException(AccountNotActivatedException except) {
        ApiError apiError = ApiError.of(
                HttpStatus.FORBIDDEN.value(),
                HttpStatus.FORBIDDEN.getReasonPhrase(),
                except.getMessage()
        );
        return new ResponseEntity<>(apiError, HttpStatus.FORBIDDEN);
    }

    /**
     * Handles EmailExistException and sends a 302 FOUND error.
     * <p>
     * This method is called automatically when the email already exists.
     * It creates an ApiError and sends it to the frontend.
     *
     * @param except The exception that was thrown (EmailExistException).
     * @return A response with an apiError and HTTP status 302 (FOUND).
     */
    @ExceptionHandler(EmailExistException.class)
    public ResponseEntity<ApiError> handleEmailExistException(EmailExistException except) {
        ApiError apiError = ApiError.of(
                HttpStatus.FOUND.value(),
                HttpStatus.FOUND.getReasonPhrase(),
                except.getMessage()
        );
        return new ResponseEntity<>(apiError, HttpStatus.FOUND);
    }

    /**
     * Handles EmailNotFoundException and sends a 404 NOT_FOUND error.
     * <p>
     * This method is called automatically when the email not found.
     * It creates an ApiError and sends it to the frontend.
     *
     * @param except The exception that was thrown (EmailNotFoundException).
     * @return A response with an apiError and HTTP status 404 (NOT_FOUND).
     */
    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<ApiError> handleNotFoundEmailException(EmailNotFoundException except) {
        ApiError apiError = ApiError.of(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                except.getMessage()
        );
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles PseudoExistException and sends a 302 FOUND error.
     * <p>
     * This method is called automatically when the pseudo already exist.
     * It creates an ApiError and sends it to the frontend.
     *
     * @param except The exception that was thrown (PseudoExistException).
     * @return A response with an apiError and HTTP status 302 (FOUND).
     */
    @ExceptionHandler(PseudoExistException.class)
    public ResponseEntity<ApiError> handlePseudoExistException(PseudoExistException except) {
        ApiError apiError = ApiError.of(
                HttpStatus.FOUND.value(),
                HttpStatus.FOUND.getReasonPhrase(),
                except.getMessage()
        );
        return new ResponseEntity<>(apiError, HttpStatus.FOUND);
    }

    /**
     * Handles PseudoNotFoundException and sends a 404 NOT_FOUND error.
     * <p>
     * This method is called automatically when the pseudo doesn't exist.
     * It creates an ApiError and sends it to the frontend.
     *
     * @param except The exception that was thrown (PseudoNotFoundException).
     * @return A response with an apiError and HTTP status 404 (NOT_FOUND).
     */
    @ExceptionHandler(PseudoNotFoundException.class)
    public ResponseEntity<ApiError> handlePseudoNotFoundException(PseudoNotFoundException except) {
        ApiError apiError = ApiError.of(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                except.getMessage()
        );
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles UserNotFoundException and sends a 404 NOT_FOUND error.
     * <p>
     * This method is called automatically when the user doesn't exist.
     * It creates an ApiError and sends it to the frontend.
     *
     * @param except The exception that was thrown (UserNotFoundException).
     * @return A response with an apiError and HTTP status 404 (NOT_FOUND).
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNotFoundException(UserNotFoundException except) {
        ApiError apiError = ApiError.of(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                except.getMessage()
        );
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles the InvalidPasswordException and sends a 401 Unauthorized error.
     * <p>
     * This method is called automatically when the user gives a wrong password.
     * It creates an ApiError and sends it to the frontend.
     *
     * @param except the exception that was thrown (InvalidPasswordException)
     * @return a ResponseEntity with an ApiError and HTTP status 401 (Unauthorized)
     */
    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ApiError> handleInvalidPasswordException(InvalidPasswordException except) {
        ApiError apiError = ApiError.of(
                HttpStatus.UNAUTHORIZED.value(),
                HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                except.getMessage()
        );
        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException except) {
        ApiError apiError = ApiError.of(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "La date de naissance doit être antérieure à la date actuelle"
        );
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ApiError> handleUserException(UserException except) {
        ApiError apiError = ApiError.of(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                except.getMessage()
        );
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
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
