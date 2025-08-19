package be.brahms.rent_serve.services.impl;

import be.brahms.rent_serve.exceptions.favor.FavorException;
import be.brahms.rent_serve.models.entities.Favor;
import be.brahms.rent_serve.repositories.FavorRepository;
import be.brahms.rent_serve.services.FavorService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation for managing favor.
 * Uses FavorRepository to perform database operations.
 */
@Service
public class FavorServiceImpl implements FavorService {

    private final FavorRepository favorRepository;

    /**
     * Constructor to create BillServiceImpl with BillRepository.
     *
     * @param favorRepository the repository to access favor data
     */
    public FavorServiceImpl(FavorRepository favorRepository) {
        this.favorRepository = favorRepository;
    }

    @Override
    public List<Favor> findAllFavour() {
        List<Favor> favors = favorRepository.findAll();

        if (favors.isEmpty()) {
            throw new FavorException("La liste est vide");
        }
        return favors;
    }
}
