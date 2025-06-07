package be.brahms.rent_server.configurations.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * This class is for JWT (JSON Web Token) configuration.
 * It helps to set up the secret key and expiration time for the JWT.
 */
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {

    /**
     * The secret key used to sign the JWT.
     * It is a byte array that starts with "secret key".
     */
    private final byte[] secret = "ThisIsAnSecretForAp-pRentServe-WithSpringbootAndAngular".getBytes(StandardCharsets.UTF_8);

    /**
     * The time in seconds before the JWT expires.
     * This is set to 10.800 seconds, which is 3 hours.
     */
    public int expireAt = 10_800; // It's 3H

    /**
     * This method returns the secret key for HMAC SHA-384.
     * It uses the secret byte array.
     */
    public SecretKey getSecretKey = new SecretKeySpec(secret, "HmacSHA384");
}
