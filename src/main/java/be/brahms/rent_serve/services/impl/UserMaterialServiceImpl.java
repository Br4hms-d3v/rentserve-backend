package be.brahms.rent_serve.services.impl;

import be.brahms.rent_serve.exceptions.material.MaterialNotFoundException;
import be.brahms.rent_serve.exceptions.user.UserException;
import be.brahms.rent_serve.exceptions.user.UserNotFoundException;
import be.brahms.rent_serve.exceptions.userMaterial.UserMaterialException;
import be.brahms.rent_serve.exceptions.userMaterial.UserMaterialIsEmptyException;
import be.brahms.rent_serve.exceptions.userMaterial.UserMaterialNotFoundException;
import be.brahms.rent_serve.models.entities.Material;
import be.brahms.rent_serve.models.entities.Picture;
import be.brahms.rent_serve.models.entities.User;
import be.brahms.rent_serve.models.entities.UserMaterial;
import be.brahms.rent_serve.repositories.MaterialRepository;
import be.brahms.rent_serve.repositories.PictureRepository;
import be.brahms.rent_serve.repositories.UserMaterialRepository;
import be.brahms.rent_serve.repositories.UserRepository;
import be.brahms.rent_serve.services.UserMaterialService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Service implementation for managing user material.
 * Uses UserMaterialRepository to perform database operations.
 */
@Service
public class UserMaterialServiceImpl implements UserMaterialService {

    private final UserMaterialRepository userMaterialRepository;
    private final MaterialRepository materialRepository;
    private final PictureRepository pictureRepository;
    private final UserRepository userRepository;

    /**
     * Constructor to create UserMaterialServiceImpl with UserMaterialRepository.
     *
     * @param userMaterialRepository Repository to manage user materials.
     * @param materialRepository     Repository to manage materials.
     * @param pictureRepository      Repository to manage pictures.
     * @param userRepository         Repository to manage users.
     */
    public UserMaterialServiceImpl(UserMaterialRepository userMaterialRepository, MaterialRepository materialRepository, PictureRepository pictureRepository, UserRepository userRepository) {
        this.userMaterialRepository = userMaterialRepository;
        this.materialRepository = materialRepository;
        this.pictureRepository = pictureRepository;
        this.userRepository = userRepository;
    }

    public List<UserMaterial> findAllUserMaterials() {
        List<UserMaterial> userMaterials = userMaterialRepository.findAll();

        if (userMaterials.isEmpty()) {
            throw new UserMaterialException("La liste est vide");
        }

        return userMaterials;
    }

    public List<UserMaterial> listUserMaterialAvailable() {
        List<UserMaterial> userMaterialsAvailable = userMaterialRepository.findAllMaterialAvailable();

        if (userMaterialsAvailable.isEmpty()) {
            throw new UserMaterialException("La liste est vide");
        }

        return userMaterialsAvailable;
    }

    public List<UserMaterial> listUserMaterialNotAvailable() {
        List<UserMaterial> userMaterialsNotAvailable = userMaterialRepository.findAllMaterialNotAvailable();

        if (userMaterialsNotAvailable.isEmpty()) {
            throw new UserMaterialException("La liste est vide");
        }

        return userMaterialsNotAvailable;
    }

    public UserMaterial findUserMaterialById(long userMaterialId) {
        return userMaterialRepository.findById(userMaterialId).orElseThrow(UserMaterialNotFoundException::new);
    }

    @Override
    public UserMaterial updateUserMaterial(long id, UserMaterial userMaterial) {

        UserMaterial userMaterialExisting = userMaterialRepository.findById(id).orElseThrow(UserMaterialNotFoundException::new);

        Long existingMaterialId = userMaterialExisting.getMaterial() != null ? userMaterialExisting.getMaterial().getId() : null;
        Long newMaterialId = userMaterial.getMaterial() != null ? userMaterial.getMaterial().getId() : null;

        if (newMaterialId != null && !newMaterialId.equals(existingMaterialId)) {

            Material newMaterial = materialRepository.findById(newMaterialId)
                    .orElseThrow(MaterialNotFoundException::new);
            userMaterial.setMaterial(newMaterial);
        } else {

            userMaterial.setMaterial(userMaterialExisting.getMaterial());
        }

        userMaterial.setId(userMaterialExisting.getId());
        userMaterial.setDescriptionMaterial(userMaterial.getDescriptionMaterial());
        userMaterial.setStateMaterial(userMaterial.getStateMaterial());
        userMaterial.setPriceHourMaterial(userMaterial.getPriceHourMaterial());
        userMaterial.setAvailable(userMaterial.isAvailable());

        userMaterial.setUser(userMaterialExisting.getUser());
        return userMaterialRepository.save(userMaterial);
    }

    @Transactional
    public void deleteUserMaterial(long id) {
        Optional<UserMaterial> userMaterialOptional = userMaterialRepository.findById(id);

        if (!userMaterialOptional.isPresent()) {
            throw new UserMaterialNotFoundException();
        }

        UserMaterial userMaterial = userMaterialOptional.get();

        Set<Picture> pictures = new HashSet<>(userMaterial.getPictures());

        for (Picture picture : pictures) {
            picture.getUserMaterial().remove(userMaterial);
            pictureRepository.delete(picture);
        }

        userMaterial.getPictures().clear();

        userMaterialRepository.delete(userMaterial);

    }

    public UserMaterial createUserMaterial(UserMaterial userMaterial) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails userDetails) {
                String username = userDetails.getUsername();
//                System.out.println(username);

                User user = userRepository.findByPseudo(username).orElseThrow(UserNotFoundException::new);
                userMaterial.setUser(user);

            }

            Material material = materialRepository.findById(userMaterial.getMaterial().getId());
            if (material == null) {
                throw new MaterialNotFoundException();
            }
            userMaterial.setMaterial(material);

            userMaterial.setDescriptionMaterial(userMaterial.getDescriptionMaterial());
            userMaterial.setStateMaterial(userMaterial.getStateMaterial());
            userMaterial.setPriceHourMaterial(userMaterial.getPriceHourMaterial());
            userMaterial.setAvailable(userMaterial.isAvailable());

            Set<Picture> persistedPictures = userMaterial.getPictures().stream()
                    .map(pictureRepository::save)
                    .collect(Collectors.toSet());
            userMaterial.setPictures(persistedPictures);

            userMaterialRepository.save(userMaterial);
        }

        return userMaterial;
    }

//    public List<UserMaterial> findAllMaterialByOwner(long userId, boolean availableOrNot) {
//
//        // TODO if exist user id continue else exception
//        // ... code here ...
//
//        List<UserMaterial> listMaterialOwner = userMaterialRepository.findAllMaterialByOwner(userId, availableOrNot);
//
//        if (listMaterialOwner.isEmpty()) {
//            throw new UserMaterialException("La liste est vide");
//        }
//
//        return listMaterialOwner;
//    }

    public List<UserMaterial> getUserMaterialsByUserId(long userId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<UserMaterial> userMaterialByUser = userMaterialRepository.findByUserId(userId);
        String checkUser = userMaterialByUser.getFirst().getUser().getPseudo();

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails userDetails) {
                String pseudo = userDetails.getUsername();
                if (!checkUser.equals(pseudo)) {
                    throw new UserException("Vous n'avez pas le droit !");
                }

            }

        }

        if (userMaterialByUser.isEmpty()) {
            throw new UserMaterialIsEmptyException();
        }

        return userMaterialByUser;
    }
}
