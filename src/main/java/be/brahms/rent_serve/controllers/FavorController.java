package be.brahms.rent_serve.controllers;

import be.brahms.rent_serve.hateaos.FavorAssembler;
import be.brahms.rent_serve.models.dtos.favor.FavorDto;
import be.brahms.rent_serve.models.entities.Favor;
import be.brahms.rent_serve.services.FavorService;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/favor/")
public class FavorController {

    private FavorService favorService;
    private FavorAssembler favorAssembler;

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
}
