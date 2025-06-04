package be.brahms.rent_server.services.impl;

import be.brahms.rent_server.repositories.BillRepository;
import be.brahms.rent_server.services.BillService;
import org.springframework.stereotype.Service;

@Service
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;

    public BillServiceImpl(BillRepository billRepository) {
        this.billRepository = billRepository;
    }
}
