package be.brahms.rent_serve.controllers;

import be.brahms.rent_serve.hateaos.FavorAssembler;
import be.brahms.rent_serve.models.dtos.favor.FavorByIdDto;
import be.brahms.rent_serve.models.dtos.favor.FavorDto;
import be.brahms.rent_serve.models.entities.Favor;
import be.brahms.rent_serve.services.FavorService;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
