package be.brahms.rent_server.configurations.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

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

    /**
     * Creates the security filter chain.
     * This method disables CSRF and CORS protection.
     * It allows all requests without restriction.
     *
     * @param http the HttpSecurity to configure
     * @return the built SecurityFilterChain
     * @throws Exception if an error occurs during configuration
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request.anyRequest().permitAll());

        return http.build();
    }

}
