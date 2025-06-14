package be.brahms.rent_serve.services.impl;

import be.brahms.rent_serve.repositories.RentalRepository;
import be.brahms.rent_serve.services.RentalService;

/**
 * Service implementation for managing rental.
 * Uses RentalRepository to perform database operations.
 */
public class RentalServiceImpl implements RentalService {

    private final RentalRepository rentalRepository;

    /**
     * Constructor to create RentalServiceImpl with RentalRepository.
     *
     * @param rentalRepository the repository to access rental data
     */
    public RentalServiceImpl(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }
}
