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

    @Override
    public Favor updateFavor(long id, Favor favor) {
        Favor updateFavor = favorRepository.findById(id).orElseThrow(FavorNotFoundException::new);
        Category existingCategory = categoryRepository.findByNameCategory(favor.getCategory().getNameCategory());

        if (updateFavor.getNameFavor().isEmpty() || updateFavor.getNameFavor().isBlank()) {
            throw new FavorException("La service ne doit pas être vide");
        }

        if (existingCategory == null) {
            throw new CategoryNotFoundException();
        }

        if (!existingCategory.getNameCategory().equals(favor.getCategory().getNameCategory())) {
            throw new CategoryNotFoundException();
        }

        updateFavor.setNameFavor(favor.getNameFavor());
        updateFavor.setAvailable(favor.isAvailable());
        updateFavor.setCategory(existingCategory);

        return favorRepository.save(updateFavor);
    }

    @Override
    public Favor deleteFavor(long id) {
        Favor deleteFavor = favorRepository.findById(id).orElseThrow(FavorNotFoundException::new);
        favorRepository.delete(deleteFavor);
        return deleteFavor;
    }

    @Override
    public List<Favor> findAllFavourByCategoryName(String categoryName) {
        List<Favor> favorListByCategory = favorRepository.findByCategoryName(categoryName);
        Category existingCategory = categoryRepository.findByNameCategory(categoryName);

        if (existingCategory == null) {
            throw new CategoryNotFoundException();
        }

        if (favorListByCategory.isEmpty()) {
            throw new FavorException("La liste est vide");
        }

        return favorListByCategory;
    }

}
