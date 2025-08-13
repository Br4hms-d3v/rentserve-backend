package be.brahms.rent_serve.controllers;

import be.brahms.rent_serve.exceptions.material.MaterialNotFoundException;
import be.brahms.rent_serve.hateaos.MaterialAssembler;
import be.brahms.rent_serve.models.dtos.material.MaterialByIdDto;
import be.brahms.rent_serve.models.dtos.material.MaterialDto;
import be.brahms.rent_serve.models.entities.Material;
import be.brahms.rent_serve.models.entities.UserMaterial;
import be.brahms.rent_serve.services.MaterialService;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('MEMBER', 'MODERATOR', 'ADMIN')")
    public ResponseEntity<EntityModel<MaterialByIdDto>> getMaterial(@PathVariable long id) {
        Material material = materialService.findById(id);

        MaterialByIdDto materialByIdDto = MaterialByIdDto.fromEntity(material);

        return ResponseEntity.ok().body(materialAssembler.toModel(materialByIdDto));
    }
}
