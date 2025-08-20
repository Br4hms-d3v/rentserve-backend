package be.brahms.rent_serve.controllers;

import be.brahms.rent_serve.hateaos.FavorAssembler;
import be.brahms.rent_serve.models.dtos.favor.FavorByIdDto;
import be.brahms.rent_serve.models.dtos.favor.FavorDto;
import be.brahms.rent_serve.models.entities.Favor;
import be.brahms.rent_serve.models.forms.favor.FavorForm;
import be.brahms.rent_serve.services.FavorService;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favor/")
public class FavorController {

    private final FavorService favorService;
    private final FavorAssembler favorAssembler;

    /**
     * This is the constructor for FavorController
     *
     * @param favorService   This is the service to manage favor
     * @param favorAssembler This helps to change favor data to link
     */
    public FavorController(FavorService favorService, FavorAssembler favorAssembler) {
        this.favorService = favorService;
        this.favorAssembler = favorAssembler;
    }

    /**
     * Get all favour from the database
     * This method returns a list of all favour
     * Each favor is converted to a FavorDto
     * Each favorDto is wrapped inside an EntityModel with HATEOAS links.
     *
     * @return ResponseEntity with a list of favor model
     */
    @GetMapping("list")
    @PreAuthorize("hasAnyRole('MEMBER', 'MODERATOR', 'ADMIN')")
    public ResponseEntity<List<EntityModel<FavorDto>>> getFavour() {
        List<Favor> favorsList = favorService.findAllFavour();

        List<FavorDto> favorDtoList = favorsList
                .stream()
                .map(FavorDto::fromEntity)
                .toList();

        List<EntityModel<FavorDto>> listFavorModel = favorDtoList
                .stream()
                .map(favorAssembler::toModel)
                .toList();

        return ResponseEntity.ok(listFavorModel);
    }

    /**
     * Get a favor by his identifier
     * This method return a favor
     * Each favor is converted to a FavorByIdDto
     * Each FavorByIdDto is wrapped inside an EntityModel with HATEOAS links
     *
     * @param id The identifier from favor
     * @return a ResponseEntity with some data from favor
     */
    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('MEMBER', 'MODERATOR', 'ADMIN')")
    public ResponseEntity<EntityModel<FavorByIdDto>> getFavourById(@PathVariable long id) {
        Favor favor = favorService.findFavorById(id);

        FavorByIdDto favorByIdDto = FavorByIdDto.fromEntity(favor);

        return ResponseEntity.ok(favorAssembler.toModel(favorByIdDto));

    }

    /**
     * Create a new favor
     * Each favor is converted to a FavorDto
     * Each favorDto is wrapped inside an EntityModel with HATEOAS links
     *
     * @param form use the form to create a new favor
     * @return the name with the new name
     */
    @PostMapping("new")
    @PreAuthorize("hasAnyRole('MEMBER', 'MODERATOR', 'ADMIN')")
    public ResponseEntity<EntityModel<FavorDto>> createFavor(@RequestBody @Valid FavorForm form) {
        Favor createFavor = favorService.create(form.toEntity());
        FavorDto favorDto = FavorDto.fromEntity(createFavor);

        EntityModel<FavorDto> FavorModel = favorAssembler.toModel(favorDto);
        return ResponseEntity.ok().body(FavorModel);
    }

    /**
     * Update the favor by his identifier
     * <p>
     * This method allows to edit a favor.
     * Check if the category exist and
     * check if the name is not null before to update </p>
     *
     * @param id   The identifier of favor
     * @param form The form to edit favor
     * @return a new name of favor after edited
     */
    @PutMapping("{id}/edit")
    @PreAuthorize("hasAnyRole('MEMBER', 'MODERATOR', 'ADMIN')")
    public ResponseEntity<EntityModel<FavorByIdDto>> updateFavor(@PathVariable long id, @RequestBody @Valid FavorForm form) {
        Favor editFavor = favorService.updateFavor(id, form.toEntity());
        FavorByIdDto favorByIdDto = FavorByIdDto.fromEntity(editFavor);

        EntityModel<FavorByIdDto> FavorModel = favorAssembler.toModel(favorByIdDto);
        return ResponseEntity.ok().body(FavorModel);
    }

    /**
     * Delete favor
     *
     * <p> This method delete a favor
     * Each FavorDto is wrapped inside an EntityModel with HATEOAS links.</p>
     *
     * @param id The identifier
     * @return a delete favor
     */
    @DeleteMapping("{id}/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EntityModel<FavorDto>> deleteFavor(@PathVariable long id) {
        Favor deleteFavor = favorService.deleteFavor(id);
        FavorDto favorDto = FavorDto.fromEntity(deleteFavor);
        favorAssembler.toModel(favorDto);
        return ResponseEntity.ok().build();
    }

}
