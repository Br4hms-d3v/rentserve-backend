package be.brahms.rent_serve.controllers;

import be.brahms.rent_serve.hateaos.MaterialAssembler;
import be.brahms.rent_serve.models.dtos.material.MaterialByIdDto;
import be.brahms.rent_serve.models.dtos.material.MaterialDto;
import be.brahms.rent_serve.models.entities.Material;
import be.brahms.rent_serve.models.forms.material.MaterialForm;
import be.brahms.rent_serve.services.MaterialService;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/material/")
public class MaterialController {

    private final MaterialService materialService;
    private final MaterialAssembler materialAssembler;

    /**
     * This is the constructor for MaterialController.
     *
     * @param materialService   This is the service to manage materials.
     * @param materialAssembler This helps to change material data to link.
     */
    public MaterialController(MaterialService materialService, MaterialAssembler materialAssembler) {
        this.materialService = materialService;
        this.materialAssembler = materialAssembler;
    }

    /**
     * Get all material from the database
     * This method returns a list of all materials
     * Each material is converted to a MaterialDto
     * Each materialDto is wrapped inside an EntityModel with HATEOAS links.
     *
     * @return ResponseEntity with a list of material model
     */
    @GetMapping("list")
    @PreAuthorize("hasAnyRole('MEMBER', 'MODERATOR', 'ADMIN')")
    public ResponseEntity<List<EntityModel<MaterialDto>>> getMaterials() {
        List<Material> materialList = materialService.findAllMaterials();

        List<MaterialDto> materialDtoList = materialList
                .stream()
                .map(MaterialDto::fromEntity)
                .toList();

        List<EntityModel<MaterialDto>> listMaterialDtoModel = materialDtoList
                .stream()
                .map(materialAssembler::toModel)
                .toList();

        return ResponseEntity.ok(listMaterialDtoModel);
    }

    /**
     * Get a material by his identifier
     * This method return a material
     * Each material is converted to a MaterialByIdDto
     * Each MaterialByIdDto is wrapped inside an EntityModel with HATEOAS links
     *
     * @param id The identifier from material
     * @return a ResponseEntity with some data from material
     */
    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('MEMBER', 'MODERATOR', 'ADMIN')")
    public ResponseEntity<EntityModel<MaterialByIdDto>> getMaterial(@PathVariable long id) {
        Material material = materialService.findById(id);

        MaterialByIdDto materialByIdDto = MaterialByIdDto.fromEntity(material);

        return ResponseEntity.ok().body(materialAssembler.toModel(materialByIdDto));
    }

    /**
     * Create a new material
     * This method create a new material
     * Each material is converted to a MaterialDto
     * Each MaterialDto is wrapped inside an EntityModel with HATEOAS links
     *
     * @param form use the formModel to create a new material
     * @return the name with the new name
     */
    @PostMapping("new")
    @PreAuthorize("hasAnyRole('MEMBER', 'MODERATOR', 'ADMIN')")
    public ResponseEntity<EntityModel<MaterialDto>> createMaterial(@RequestBody @Valid MaterialForm form) {
        Material createMaterial = materialService.create(form.toEntity());
        MaterialDto materialDto = MaterialDto.fromEntity(createMaterial);

        EntityModel<MaterialDto> materialModel = materialAssembler.toModel(materialDto);
        return ResponseEntity.ok().body(materialModel);
    }

    /**
     * Update the material by his identifier
     *
     * <p>This method allows to edit a material.
     * Check if the category exist and
     * check if the name is not null before to update </p>
     *
     * @param id   the identifier of material
     * @param form the form to edit material
     * @return a new name of material after edited
     */
    @PutMapping("{id}/edit")
    @PreAuthorize("hasAnyRole('MEMBER', 'MODERATOR', 'ADMIN')")
    public ResponseEntity<EntityModel<MaterialByIdDto>> updateMaterial(@PathVariable long id, @RequestBody @Valid MaterialForm form) {
        Material editMaterial = materialService.updateMaterial(id, form.toEntity());
        MaterialByIdDto materialByIdDto = MaterialByIdDto.fromEntity(editMaterial);
        EntityModel<MaterialByIdDto> materialModel = materialAssembler.toModel(materialByIdDto);
        return ResponseEntity.ok().body(materialModel);
    }

    /**
     * Delete material
     *
     * <p> This method delete a material
     * Each MaerialDto is wrapped inside an EntityModel with HATEOAS links.</p>
     *
     * @param id The identifier
     * @return an delete material
     */
    @DeleteMapping("{id}/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EntityModel<MaterialDto>> deleteMaterial(@PathVariable long id) {
        Material deleteMaterial = materialService.deleteMaterial(id);
        MaterialDto materialDto = MaterialDto.fromEntity(deleteMaterial);
        materialAssembler.toModel(materialDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/category/{nameCategory}")
    @PreAuthorize("hasAnyRole('MEMBER', 'MODERATOR', 'ADMIN')")
    public ResponseEntity<List<EntityModel<MaterialDto>>> getMaterialByNameCategory(@PathVariable String nameCategory) {
        List<Material> listMaterialByNameCategory = materialService.findAllMaterialsByCategoryName(nameCategory);
        List<MaterialDto> listMaterialByCategoryNameDto = listMaterialByNameCategory
                .stream()
                .map(MaterialDto::fromEntity)
                .toList();

        List<EntityModel<MaterialDto>> listMaterialModel = listMaterialByCategoryNameDto
                .stream()
                .map(materialAssembler::toModel)
                .toList();

        return ResponseEntity.ok().body(listMaterialModel);
    }


}
