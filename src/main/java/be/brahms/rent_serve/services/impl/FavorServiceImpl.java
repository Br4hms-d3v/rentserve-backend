package be.brahms.rent_serve.services.impl;

import be.brahms.rent_serve.exceptions.category.CategoryNotFoundException;
import be.brahms.rent_serve.exceptions.favor.FavorException;
import be.brahms.rent_serve.exceptions.favor.FavorExistException;
import be.brahms.rent_serve.exceptions.favor.FavorNotFoundException;
import be.brahms.rent_serve.models.entities.Category;
import be.brahms.rent_serve.models.entities.Favor;
import be.brahms.rent_serve.repositories.CategoryRepository;
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
    private final CategoryRepository categoryRepository;

    /**
     * Constructor to create BillServiceImpl with BillRepository.
     *
     * @param favorRepository    the repository to access favor data
     * @param categoryRepository the repository to acces favor d
     */
    public FavorServiceImpl(FavorRepository favorRepository, CategoryRepository categoryRepository) {
        this.favorRepository = favorRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Favor> findAllFavour() {
        List<Favor> favors = favorRepository.findAll();

        if (favors.isEmpty()) {
            throw new FavorException("La liste est vide");
        }
        return favors;
    }

    @Override
    public Favor findFavorById(long id) {

        return favorRepository.findById(id).orElseThrow(FavorNotFoundException::new);

    }

    @Override
    public Favor create(Favor favor) {

        String nameCategory = favor.getCategory().getNameCategory();

        Category existingCategory = categoryRepository.findByNameCategory(nameCategory);

        if (!categoryRepository.existsByNameCategory(nameCategory)) {
            throw new CategoryNotFoundException();
        }

        if (favorRepository.existsFavorByNameFavor(favor.getNameFavor())) {
            throw new FavorExistException();
        }

        if (favor.getNameFavor().isEmpty() || favor.getNameFavor().isBlank()) {
            throw new FavorNotFoundException();
        }

        favor.setCategory(existingCategory);
        favor.setAvailable(true);

        return favorRepository.save(favor);

    }

}
