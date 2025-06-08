package be.brahms.rent_server.configurations.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * This class configures security settings for the application.
 */
@Configuration
public class SecurityConf {

    /**
     * Constructor by default for SecurityConf
     */
    public SecurityConf() {
    }

    /**
     * Creates a BCryptPasswordEncoder bean.
     *
     * @return A new BCryptPasswordEncoder object.
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
