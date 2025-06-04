package be.brahms.rent_server.services.impl;

import be.brahms.rent_server.repositories.PictureRepository;
import be.brahms.rent_server.services.PictureService;
import org.springframework.stereotype.Service;

@Service
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;

    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }
}
