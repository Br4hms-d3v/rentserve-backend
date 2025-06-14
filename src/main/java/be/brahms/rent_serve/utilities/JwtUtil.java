package be.brahms.rent_serve.utilities;

import be.brahms.rent_serve.configurations.security.JwtConfig;
import be.brahms.rent_serve.models.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * This class provides utility methods for working with JSON Web Tokens (JWT).
 * It includes methods for generating and validating JWTs.
 */
@Component
public class JwtUtil {

    private final JwtConfig jwtConfig; // Configuration for JWT operation
    private final JwtParser jwtParser; // Jwt parser for decoding token
    private final JwtBuilder jwtBuilder; // Jwt builder for generating token

    /**
     * Constructs for JwtUtil.
     *
     * @param config The JwtConf object containing configuration details.
     */
    public JwtUtil(JwtConfig config) {
        this.jwtConfig = config;
        this.jwtParser = Jwts.parser().verifyWith(config.getSecretKey).build();
        this.jwtBuilder = Jwts.builder().signWith(jwtConfig.getSecretKey);
    }

    /**
     * Generates a JWT for the given User Entity object.
     *
     * @param user The User Entity object containing user information.
     * @return A string representation of the generated JWT.
     */
    public String generateToken(User user) {
        return jwtBuilder.claim("email", user.getEmail())
                .claim("pseudo", user.getPseudo())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtConfig.expireAt * 1_000L))
                .compact();
    }

    /**
     * Retrieves claims from the given JWT token.
     * Recup reclamation
     *
     * @param token The JWT string to be parsed.
     * @return Claims extracted from the token.
     */
    public Claims getClaims(String token) {
        return jwtParser.parseSignedClaims(token).getPayload();
    }

    /**
     * Retrieves the email from the given JWT token.
     *
     * @param token The JWT string to be parsed.
     * @return The email contained in the token.
     */
    public String getEmail(String token) {
        return getClaims(token).get("email", String.class);
    }

    /**
     * Retrieves the pseudo from the given JWT token.
     *
     * @param token The JWT string to be parsed.
     * @return The pseudo contained in the token.
     */
    public String getPseudo(String token) {
        return getClaims(token).get("pseudo", String.class);
    }

    /**
     * Checks the validity of a JSON Web Token (JWT).
     *
     * @param token The JWT string to be validated.
     * @return True if the token is valid; false otherwise.
     */
    public boolean isValidToken(String token) {
        Claims claims = getClaims(token);
        Date dateNow = new Date();
        return dateNow.after(claims.getIssuedAt()) && dateNow.before(claims.getExpiration());
    }
}