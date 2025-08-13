package be.brahms.rent_serve.models.dtos;

import java.util.List;

public record UserMaterialDto(
        long id,
        long ownerMaterialId,
        String ownerUserName,
        List<String> pictures
) {
}
