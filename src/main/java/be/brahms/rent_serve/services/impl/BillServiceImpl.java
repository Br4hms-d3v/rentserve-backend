package be.brahms.rent_serve.services.impl;

import be.brahms.rent_serve.repositories.BillRepository;
import be.brahms.rent_serve.services.BillService;
import org.springframework.stereotype.Service;

/**
 * Service implementation for managing bills.
 * Uses BillRepository to perform database operations.
 */
@Service
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;

    /**
     * Constructor to create BillServiceImpl with BillRepository.
     *
     * @param billRepository the repository to access bill data
     */
    public BillServiceImpl(BillRepository billRepository) {
        this.billRepository = billRepository;
    }
}
