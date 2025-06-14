package be.brahms.rent_serve.services.impl;

import be.brahms.rent_serve.repositories.PictureRepository;
import be.brahms.rent_serve.services.PictureService;
import org.springframework.stereotype.Service;

/**
 * Service implementation for managing picture.
 * Uses PictureRepository to perform database operations.
 */
@Service
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;

    /**
     * Constructor to create PictureServiceImpl with PictureRepository.
     *
     * @param pictureRepository the repository to access picture data
     */
    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }
}
