package be.brahms.rent_server.services.impl;

import be.brahms.rent_server.repositories.ReviewRepository;
import be.brahms.rent_server.services.ReviewService;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }
}
