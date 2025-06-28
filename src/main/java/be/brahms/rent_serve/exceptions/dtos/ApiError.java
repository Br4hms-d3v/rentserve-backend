package be.brahms.rent_serve.exceptions.dtos;

/**
 * ApiError is a record used to send error information from the backend to the frontend.
 * <p>
 * It contains the HTTP status code, a short error label, and a detailed message.
 * This record helps the frontend show clear error messages to the user.
 *
 * @param status  the HTTP status code (example: 404, 401, 500)
 * @param error   a short name for the error (example: "Not Found", "Unauthorized")
 * @param message the full error message from the backend or exception
 */
public record ApiError(
        int status, // Code HTTP (ex: 404, 403...)
        String error, // Label of error HTTP (ex: Not_Found)
        String message // Message from Exception (ex: Not found user)
) {
    /**
     * Creates a new ApiError with the given status, error label, and message.
     *
     * @param status  the HTTP status code (example: 403)
     * @param error   the short label of the HTTP error (example: "Forbidden")
     * @param message the custom message from the exception or backend (example: "Account not activated")
     * @return a new ApiError object with the given values
     */
    public static ApiError of(int status, String error, String message) {
        return new ApiError(status, error, message);
    }
}
