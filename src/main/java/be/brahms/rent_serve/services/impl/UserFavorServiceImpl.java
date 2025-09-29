package be.brahms.rent_serve.services.impl;

import be.brahms.rent_serve.exceptions.favor.FavorNotFoundException;
import be.brahms.rent_serve.exceptions.user.UserException;
import be.brahms.rent_serve.exceptions.userFavor.UserFavorException;
import be.brahms.rent_serve.exceptions.userFavor.UserFavorNotFoundException;
import be.brahms.rent_serve.exceptions.userFavor.UserFavourEmptyException;
import be.brahms.rent_serve.models.entities.Favor;
import be.brahms.rent_serve.models.entities.Picture;
import be.brahms.rent_serve.models.entities.UserFavor;
import be.brahms.rent_serve.repositories.FavorRepository;
import be.brahms.rent_serve.repositories.PictureRepository;
import be.brahms.rent_serve.repositories.UserFavorRepository;
import be.brahms.rent_serve.services.UserFavorService;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Service implementation for managing user favor.
 * Uses UserFavorRepository to perform database operations.
 */
@Service
public class UserFavorServiceImpl implements UserFavorService {

    private final UserFavorRepository userFavorRepository;
    private final FavorRepository favorRepository;
    private final PictureRepository pictureRepository;

    /**
     * Constructor to create UserFavorServiceImpl with UserFavorRepository.
     *
     * @param userFavorRepository the repository to access user favor data
     */
    public UserFavorServiceImpl(UserFavorRepository userFavorRepository, FavorRepository favorRepository, PictureRepository pictureRepository) {
        this.userFavorRepository = userFavorRepository;
        this.favorRepository = favorRepository;
        this.pictureRepository = pictureRepository;
    }

    @Override
    public List<UserFavor> findAllUserFavour() {
        List<UserFavor> userFavors = userFavorRepository.findAll();

        if (userFavors.isEmpty()) {
            throw new UserFavourEmptyException();
        }
        return userFavors;
    }

    @Override
    public List<UserFavor> findAllUserFavourById(@Param("favorId") long favorId) {
        List<UserFavor> userFavour = userFavorRepository.findByFavorId(favorId);

        if (!userFavorRepository.existsById(favorId)) {
            throw new FavorNotFoundException();
        }

        if (userFavour.isEmpty()) {
            throw new UserFavourEmptyException();
        }

        userFavour.stream()
                .findFirst()
                .ifPresent(user -> {
                    if (!userFavorRepository.existsById(user.getId())) {
                        throw new UserFavorException("Le service n'existe pas");
                    }
                });

        return userFavour;
    }

    @Override
    public List<UserFavor> findAllUserFavourAvailable() {

        List<UserFavor> listUserFavorAvailable = userFavorRepository.findAllUserFavorAvailable();

        if (listUserFavorAvailable.isEmpty()) {
            throw new UserFavourEmptyException();
        }
        return listUserFavorAvailable;
    }

    @Override
    public List<UserFavor> findAllUserFavourNotAvailable() {

        List<UserFavor> listUserFavorNotAvailable = userFavorRepository.findAllUserFavorNotAvailable();

        if (listUserFavorNotAvailable.isEmpty()) {
            throw new UserFavourEmptyException();
        }
        return listUserFavorNotAvailable;
    }

    @Override
    public UserFavor findUserFavorById(long favorId) {
        return userFavorRepository.findById(favorId).orElseThrow(UserFavorNotFoundException::new);
    }

    @Override
    public List<UserFavor> getUserFavorByUserId(long userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<UserFavor> userFavourListByUser = userFavorRepository.findByUserId(userId);
        String checkUser = userFavourListByUser.getFirst().getUser().getPseudo();

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails userDetails) {
                String pseudo = userDetails.getUsername();

                if (!checkUser.equals(pseudo)) {
                    throw new UserException("Vous n'avez pas le droit !!");
                }
            }
        }

        if (userFavourListByUser.isEmpty()) {
            throw new UserFavourEmptyException();
        }
        return userFavourListByUser;
    }

    @Override
    public UserFavor updateUserFavor(long id, UserFavor userFavor) {

        UserFavor userFavorExisting = userFavorRepository.findById(id).orElseThrow(UserFavorNotFoundException::new);

        Long existingFavorId = userFavorExisting.getFavor() != null ? userFavorExisting.getFavor().getId() : null;
        Long newFavorId = userFavor.getFavor() != null ? userFavor.getFavor().getId() : null;

        if (newFavorId != null && !newFavorId.equals(existingFavorId)) {
            Favor newFavor = favorRepository.findById(newFavorId).orElseThrow(FavorNotFoundException::new);
            userFavor.setFavor(newFavor);
        } else {
            userFavor.setFavor(userFavorExisting.getFavor());
        }

        userFavor.setId(userFavorExisting.getId());
        userFavor.setDescriptionFavor(userFavor.getDescriptionFavor());
        userFavor.setPriceHourFavor(userFavor.getPriceHourFavor());
        userFavor.setAvailable(userFavor.isAvailable());

        userFavor.setUser(userFavorExisting.getUser());

        return userFavorRepository.save(userFavor);
    }

    @Transactional
    public void deleteUserFavor(long id) {
        Optional<UserFavor> userFavorOptional = userFavorRepository.findById(id);

        if (!userFavorOptional.isPresent()) {
            throw new UserFavorNotFoundException();
        }

        UserFavor userFavor = userFavorOptional.get();

        Set<Picture> pictures = new HashSet<>(userFavor.getPictures());

        for (Picture picture : pictures) {
            picture.getUserFavor().remove(userFavor);
            pictureRepository.delete(picture);
        }

        userFavor.getPictures().clear();

        userFavorRepository.delete(userFavor);
    }
}
