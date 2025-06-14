package be.brahms.rent_serve.services.impl;

import be.brahms.rent_serve.repositories.ReviewRepository;
import be.brahms.rent_serve.services.ReviewService;
import org.springframework.stereotype.Service;

/**
 * Service implementation for managing review.
 * Uses ReviewRepository to perform database operations.
 */
@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    /**
     * Constructor to create ReviewServiceImpl with ReviewRepository.
     *
     * @param reviewRepository the repository to access review data
     */
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }
}
