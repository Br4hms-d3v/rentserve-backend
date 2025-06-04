package be.brahms.rent_server.services.impl;

import be.brahms.rent_server.repositories.RentalRepository;
import be.brahms.rent_server.services.RentalService;

public class RentalServiceImpl implements RentalService {

    private final RentalRepository rentalRepository;

    public RentalServiceImpl(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }
}
