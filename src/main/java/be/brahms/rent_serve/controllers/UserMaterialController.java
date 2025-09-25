package be.brahms.rent_serve.controllers;

import be.brahms.rent_serve.hateaos.UserMaterialAssembler;
import be.brahms.rent_serve.models.dtos.userMaterial.UserMaterialByIdDto;
import be.brahms.rent_serve.models.dtos.userMaterial.UserMaterialDeleteDto;
import be.brahms.rent_serve.models.dtos.userMaterial.UserMaterialDto;
import be.brahms.rent_serve.models.dtos.userMaterial.UserMaterialGroupByMaterialDto;
import be.brahms.rent_serve.models.entities.UserMaterial;
import be.brahms.rent_serve.models.forms.userMaterial.UserMaterialCreateForm;
import be.brahms.rent_serve.models.forms.userMaterial.UserMaterialForm;
import be.brahms.rent_serve.services.UserMaterialService;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This controller manage UserMaterial
 * <ul>
 *     <li> Get a list of materials form all users </li>
 *     <li> Get a list of materials available is true </li>
 *     <li> Get a list of materials available is false </li>
 *     <li> Get user material by ID </li>
 *     <li> Update the material by ID </li>
 *     <li> Delete a material </li>
 *     <li> Create a new material from user </li>
 *     <li> Get a list of material grouped by material (only owner can see his materials) </li>
 * </ul>
 */
@RestController
@RequestMapping("/api/user-material/")
public class UserMaterialController {

    private final UserMaterialService userMaterialService; // Service to manage user materials
    private final UserMaterialAssembler userMaterialAssembler; // Helper to convert UserMaterial objects to DTOs

    /**
     * Creates a UserMaterialController.
     *
     * @param userMaterialService   The service to manage user materials.
     * @param userMaterialAssembler The assembler to make models from user materials.
     */
    public UserMaterialController(UserMaterialService userMaterialService, UserMaterialAssembler userMaterialAssembler) {
        this.userMaterialService = userMaterialService;
        this.userMaterialAssembler = userMaterialAssembler;
    }

    /**
     * Get all materials form the DB
     * <p>
     * This method returns a list of all user materials
     * Each user material is converted to a UserMaterialDto
     * Each UserMaterialDto is wrapped inside an EntityModel HATEOAS links
     * </p>
     *
     * @return ResponseEntity with a list of user material
     */
    @GetMapping("list")
    @PreAuthorize("hasAnyRole('MEMBER','MODERATOR','ADMIN')")
    public ResponseEntity<List<EntityModel<UserMaterialDto>>> getUserMaterials() {
        List<UserMaterial> userMaterialList = userMaterialService.findAllUserMaterials();
        List<UserMaterialDto> userMaterialDtoList = userMaterialList
                .stream()
                .map(UserMaterialDto::fromEntity)
                .toList();

        List<EntityModel<UserMaterialDto>> listUserMaterialModel = userMaterialDtoList
                .stream()
                .map(userMaterialAssembler::toModel)
                .toList();

        return ResponseEntity.ok(listUserMaterialModel);
    }

    /**
     * Gets a list of all activated user materials.
     * Only users with role MEMBER, MODERATOR, or ADMIN can use this.
     *
     * @return A response with a list of user materials that are active.
     */
    @GetMapping("list/activated")
    @PreAuthorize("hasAnyRole('MEMBER','MODERATOR','ADMIN')")
    public ResponseEntity<List<EntityModel<UserMaterialDto>>> getActivatedUserMaterials() {
        List<UserMaterial> userMaterialActivated = userMaterialService.listUserMaterialAvailable();
        List<UserMaterialDto> userMaterialActivatedDtoList = userMaterialActivated
                .stream()
                .map(UserMaterialDto::fromEntity)
                .toList();

        List<EntityModel<UserMaterialDto>> entityUserMaterialActivated = userMaterialActivatedDtoList
                .stream()
                .map(userMaterialAssembler::toModel)
                .toList();

        return ResponseEntity.ok(entityUserMaterialActivated);
    }

    /**
     * Gets a list of all deactivated user materials.
     * Only users with role MEMBER, MODERATOR, or ADMIN can use this.
     *
     * @return A response with a list of user materials that are not active.
     */
    @GetMapping("list/deactivated")
    @PreAuthorize("hasAnyRole('MEMBER','MODERATOR','ADMIN')")
    public ResponseEntity<List<EntityModel<UserMaterialDto>>> getDeactivatedUserMaterials() {
        List<UserMaterial> userMaterialDeactivated = userMaterialService.listUserMaterialNotAvailable();
        List<UserMaterialDto> userMaterialDeactivatedDtoList = userMaterialDeactivated
                .stream()
                .map(UserMaterialDto::fromEntity)
                .toList();

        List<EntityModel<UserMaterialDto>> entityUserMaterialDeactivated = userMaterialDeactivatedDtoList
                .stream()
                .map(userMaterialAssembler::toModel)
                .toList();

        return ResponseEntity.ok(entityUserMaterialDeactivated);
    }

    /**
     * Gets user material.
     * Only users with role MEMBER, MODERATOR, or ADMIN can use this.
     *
     * @param id The ID of the user material identifier to update
     * @return A response with a details of user materials that is selected by his ID.
     */
    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('MEMBER','MODERATOR','ADMIN')")
    public ResponseEntity<EntityModel<UserMaterialByIdDto>> getUserMaterial(@PathVariable long id) {
        UserMaterial userMaterial = userMaterialService.findUserMaterialById(id);
        UserMaterialByIdDto userMaterialByIdDto = UserMaterialByIdDto.fromEntity(userMaterial);

        return ResponseEntity.ok().body(userMaterialAssembler.toModel(userMaterialByIdDto));
    }

    /**
     * Update user materials by its ID.
     * Only users with role MEMBER, MODERATOR, or ADMIN can use this.
     *
     * @param id   The ID of the user material to update.
     * @param form The new data to update the user material.
     * @return A response with the updated user material model.
     * @throws AccessDeniedException If the user is not allowed to update.
     */
    @PutMapping("{id}/edit")
    @PreAuthorize("hasAnyRole('MEMBER','MODERATOR','ADMIN')")
    public ResponseEntity<EntityModel<UserMaterialDto>> updateUserMaterial(@PathVariable long id, @RequestBody @Valid UserMaterialForm form) throws AccessDeniedException {
        UserMaterial editUserMaterial = userMaterialService.updateUserMaterial(id, form.toEntity());
        UserMaterialDto userMaterialDto = UserMaterialDto.fromEntity(editUserMaterial);
        EntityModel<UserMaterialDto> userMaterialDtoEntityModel = userMaterialAssembler.toModel(userMaterialDto);
        return ResponseEntity.ok().body(userMaterialDtoEntityModel);
    }

    /**
     * Delete user material by its ID.
     * Only users with role MEMBER, MODERATOR, or ADMIN can use this.
     *
     * @param id The ID of the user material identifier to delete
     * @return A response with a message to success to delete.
     */
    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('MEMBER','MODERATOR','ADMIN')")
    public ResponseEntity<EntityModel<UserMaterialDeleteDto>> deleteUserMaterial(@PathVariable long id) {
        userMaterialService.deleteUserMaterial(id);
        UserMaterialDeleteDto userMaterialDeleteDto = UserMaterialDeleteDto.successDeleting();
        EntityModel<UserMaterialDeleteDto> userMaterialDeleteDtoEntityModel = userMaterialAssembler.toModel(userMaterialDeleteDto);
        return ResponseEntity.ok(userMaterialDeleteDtoEntityModel);
    }

    /**
     * Creates a new user material.
     * Only users with role MEMBER, MODERATOR, or ADMIN can do this.
     *
     * @param form The form with the data to create the new user material.
     * @return A response with the created user material model.
     */
    @PostMapping("new")
    @PreAuthorize("hasAnyRole('MEMBER','MODERATOR','ADMIN')")
    public ResponseEntity<EntityModel<UserMaterialDto>> createUserMaterial(@RequestBody @Valid UserMaterialCreateForm form) {
        UserMaterial createUserMaterial = userMaterialService.createUserMaterial(form.toEntity());
        UserMaterialDto userMaterialDto = UserMaterialDto.fromEntity(createUserMaterial);

        EntityModel<UserMaterialDto> userMaterialDtoEntityModel = userMaterialAssembler.toModel(userMaterialDto);

        return ResponseEntity.ok().body(userMaterialDtoEntityModel);
    }

//    @GetMapping("{id}/owner")
//    public ResponseEntity<EntityModel<UserMaterialDto>> getUserMaterialByOwner(@PathVariable Long id) {
//        UserMaterial userMaterial = userMaterialService.findUserMaterialById(id);
//        UserMaterialDto userMaterialDto = UserMaterialDto.fromEntity(userMaterial);
//        EntityModel<UserMaterialDto> userMaterialDtoEntityModel = userMaterialAssembler.toModel(userMaterialDto);
//        return ResponseEntity.ok().body(userMaterialDtoEntityModel);
//    }

    /**
     * Gets all user materials for one user, grouped by material type.
     * Only users with role MEMBER, MODERATOR, or ADMIN can do this.
     *
     * @param userId The ID of the user.
     * @return A response with a list of grouped user materials.
     */
    @GetMapping("/grouped/{userId}")
    @PreAuthorize("hasAnyRole('MEMBER','MODERATOR','ADMIN')")
    public ResponseEntity<List<UserMaterialGroupByMaterialDto>> getGroupedMaterials(@PathVariable Long userId) {
        List<UserMaterial> allUserMaterials = userMaterialService.getUserMaterialsByUserId(userId);

        // Group the materials by material ID
        Map<Long, List<UserMaterial>> grouped = allUserMaterials.stream()
                .collect(Collectors.groupingBy(um -> um.getMaterial().getId()));

        // Convert each group to a DTO (data object)
        List<UserMaterialGroupByMaterialDto> result = grouped.values().stream()
                .map(UserMaterialGroupByMaterialDto::fromGroup)
                .toList();

        return ResponseEntity.ok(result);
    }

}
