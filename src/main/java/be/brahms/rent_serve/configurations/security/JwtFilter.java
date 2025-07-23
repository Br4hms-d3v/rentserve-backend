package be.brahms.rent_serve.configurations.security;

import be.brahms.rent_serve.utilities.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * This class is a filter that checks the JWT (JSON Web Token) in the request header.
 * It is used to authenticate the user and set the security context if the token is valid.
 * <p>
 * It extends OncePerRequestFilter to ensure the filter runs once per request.
 */
@Configuration
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    /**
     * Constructor for JwtFilter.
     *
     * @param jwtUtil            The utility class for handling JWT operations.
     * @param userDetailsService The service for loading user details.
     */
    @Autowired
    public JwtFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    /**
     * This method is called for every HTTP request.
     * It checks if there is a JWT token in the request and validates it.
     * If the token is valid, it sets the authentication in the security context.
     *
     * @param request     The HTTP request.
     * @param response    The HTTP response.
     * @param filterChain The filter chain that allows the request to continue.
     * @throws ServletException If a servlet error occurs.
     * @throws IOException      If an I/O error occurs.
     */
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization"); // Get the "authorization" for the request from header

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String[] authorizations = authorizationHeader.split(" ");

            // Vérification de la taille du tableau pour éviter ArrayIndexOutOfBoundsException
            if (authorizations.length == 2) {
                String type = authorizations[0]; // "Bearer"
                String token = authorizations[1]; // Le JWT token

                if (type.equals("Bearer") && !token.isEmpty()) { // Check if it's a Bearer token and not empty
                    if (jwtUtil.isValidToken(token)) { // Check valid token
                        String pseudo = jwtUtil.getPseudo(token);
                        System.out.println("Token is valid for pseudo: " + pseudo);  // Log

                        UserDetails user = userDetailsService.loadUserByUsername(pseudo); // Load user details by pseudo
                        System.out.println("User roles: " + user.getAuthorities());  // Log des rôles de l'utilisateur

                        UsernamePasswordAuthenticationToken uPaT = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(uPaT); // Set authentication in security context
                    }
                }
            } else {
                // Si le format est incorrect (par exemple, il n'y a pas de token après "Bearer")
                throw new ServletException("Invalid Authorization header format.");
            }
        } else {
            // Si l'en-tête Authorization est manquant ou mal formé
            System.out.println("Missing or incorrect Authorization header.");
        }

        filterChain.doFilter(request, response); // continue the filter chain
    }

}