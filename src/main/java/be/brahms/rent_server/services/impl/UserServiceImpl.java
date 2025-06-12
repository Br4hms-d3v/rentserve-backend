package be.brahms.rent_server.services.impl;

import be.brahms.rent_server.enums.Role;
import be.brahms.rent_server.exceptions.user.*;
import be.brahms.rent_server.models.dtos.EmailTokenDTO;
import be.brahms.rent_server.models.entities.User;
import be.brahms.rent_server.repositories.UserRepository;
import be.brahms.rent_server.services.UserService;
import be.brahms.rent_server.services.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service implementation for managing users.
 * Uses UserRepository to perform database operations.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EmailService emailService;


    /**
     * Constructor to create UserServiceImpl with UserRepository.
     *
     * @param userRepository        the repository to access user data
     * @param bCryptPasswordEncoder encode password with BCrypt
     * @param emailService          send an email for confirmation of registration
     */
    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, EmailService emailService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.emailService = emailService;
    }

    /**
     * This method registers a new user.
     * It sets the role to MEMBER, hashes the password,
     * and makes the user not active.
     * It also makes a token (not used here).
     * Then it saves the user.
     * Send an email for active the account
     *
     * @param user the user to register
     * @return the saved user
     */
    @Override
    public User register(User user) {

        // Check if exist an email
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailExistException();
        }

        // Check if exist this pseudo
        if (userRepository.existsByPseudo(user.getPseudo())) {
            throw new PseudoExistException();
        }

        // User start has a member
        user.setRole(Role.MEMBER);
        // Hash the password
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        // User is not active but it is created
        user.setIsActive(false);

        // Generate a token
        EmailTokenDTO emailTokenDTO = new EmailTokenDTO();

        // Send email for confirmation of registration account
        String confirmationUrl = "http://localhost:8080/api/mail/confirmation?token=" + emailTokenDTO.confirmationToken() + "&email=" + user.getEmail();

        emailService.sendMailConfirmation(user.getEmail(), confirmationUrl);

        return userRepository.save(user);
    }

    @Override
    public void activateUser(String email) {
        User userActivate = userRepository.findByEmail(email);

        if (userActivate == null) {
            throw new EmailNotFoundException();
        }
        userActivate.setIsActive(true);

        userRepository.save(userActivate);
    }

    @Override
    public User login(User user) {

        Optional<User> foundEmailOrPseudo = userRepository.findByEmailOrPseudo(user.getEmail(), user.getPseudo());

        if (foundEmailOrPseudo.isEmpty()) {

            boolean userLoginWithEmail = userRepository.existsByEmail(user.getEmail());
            boolean userLoginwithPseudo = userRepository.existsByPseudo(user.getPseudo());

            // Check if the email or pseudo are available in the DB
            if (!userLoginWithEmail && user.getEmail() != null) {
                throw new EmailNotFoundException();
            }
            if (!userLoginwithPseudo && user.getPseudo() != null) {
                throw new PseudoNotFoundException();
            }
        }

        User userLogin = foundEmailOrPseudo.get();

        // Check if the user activated his account
        if (!userLogin.getIsActive()) {
            throw new AccountNotActivatedException();
        }

        if (!bCryptPasswordEncoder.matches(user.getPassword(), userLogin.getPassword())) {
            throw new RuntimeException("Le mot de passe n'existe pas");
        }

        return userLogin;
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public List<User> findAllUsers() {

        List<User> listOfUsers = userRepository.findAll();

        if (listOfUsers.isEmpty()) {
            throw new UserException("La liste est vide !");
        }
        return listOfUsers;
    }

    @Override
    public List<User> findAllUsersByRole(Role role) {
        List<User> listOfUserByRole = userRepository.listUsersByRole(role);

        if (listOfUserByRole.isEmpty()) {
            throw new UserException("Aucun n'a le role : " + role);
        }

        return listOfUserByRole;
    }

    // It is from by UseDetailService

    /**
     * This method finds a user by username.
     * Now, it returns nothing (null).
     *
     * @param username the username to find
     * @return the user details or null
     * @throws UsernameNotFoundException if user is not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
