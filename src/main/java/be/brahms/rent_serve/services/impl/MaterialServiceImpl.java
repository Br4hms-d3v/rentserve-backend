package be.brahms.rent_serve.services.impl;

import be.brahms.rent_serve.exceptions.category.CategoryNotFoundException;
import be.brahms.rent_serve.exceptions.material.MaterialException;
import be.brahms.rent_serve.exceptions.material.MaterialExistException;
import be.brahms.rent_serve.exceptions.material.MaterialNotFoundException;
import be.brahms.rent_serve.models.entities.Category;
import be.brahms.rent_serve.models.entities.Material;
import be.brahms.rent_serve.repositories.CategoryRepository;
import be.brahms.rent_serve.repositories.MaterialRepository;
import be.brahms.rent_serve.services.MaterialService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation for managing material.
 * Uses MaterialRepository to perform database operations.
 */
@Service
public class MaterialServiceImpl implements MaterialService {

    private final MaterialRepository materialRepository;
    private final CategoryRepository categoryRepository;

    /**
     * Constructor to create MaterialServiceImpl with MaterialRepository.
     *
     * @param materialRepository the repository to access material data
     */
    public MaterialServiceImpl(MaterialRepository materialRepository, CategoryRepository categoryRepository) {
        this.materialRepository = materialRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Material> findAllMaterials() {
        return materialRepository.findAll();
    }

    @Override
    public Material findById(long id) {

        return materialRepository.findById(id).orElseThrow(MaterialNotFoundException::new);
    }

    @Override
    public Material create(Material material) {

        String nameCategory = material.getCategory().getNameCategory();

        Category existingCategory = categoryRepository.findByNameCategory(nameCategory);

        if (!categoryRepository.existsByNameCategory(material.getCategory().getNameCategory())) {
            throw new CategoryNotFoundException();
        }

        if (materialRepository.existsMaterialByNameMaterial(material.getNameMaterial())) {
            throw new MaterialExistException();
        }

        if (material.getNameMaterial().isEmpty()) {
            throw new MaterialNotFoundException();
        }

        material.setCategory(existingCategory);
        material.setAvailable(true);

        return materialRepository.save(material);
    }

    @Override
    public Material updateMaterial(long id, Material material) {

        Material updateMaterial = materialRepository.findById(id).orElseThrow(MaterialNotFoundException::new);
        Category existingCategory = categoryRepository.findByNameCategory(material.getCategory().getNameCategory());

        if (updateMaterial.getCategory().getNameCategory().isEmpty()) {
            throw new MaterialException("La category ne doit pas être vide");
        }
        if (!existingCategory.getNameCategory().equals(material.getCategory().getNameCategory())) {
            throw new CategoryNotFoundException();
        }

        updateMaterial.setNameMaterial(material.getNameMaterial());
        updateMaterial.setCategory(existingCategory);

        return materialRepository.save(updateMaterial);
    }

    @Override
    public Material deleteMaterial(long id) {

        Material deleteMaterial = materialRepository.findById(id).orElseThrow(MaterialNotFoundException::new);

        materialRepository.delete(deleteMaterial);

        return deleteMaterial;
    }


}
