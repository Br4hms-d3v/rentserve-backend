package be.brahms.rent_serve.models.dtos.userFavor;

import be.brahms.rent_serve.models.dtos.favor.FavorDto;
import be.brahms.rent_serve.models.entities.UserFavor;

import java.util.List;

public record UserFavourGroupedByFavourDto(
        FavorDto favor,
        List<UserFavorDto> userFavour
) {
    public static UserFavourGroupedByFavourDto fromGroup(List<UserFavor> userFavour) {
        if (userFavour == null || userFavour.isEmpty()) {
//            return new UserFavourEmptyException();
        }

        FavorDto favorDto = FavorDto.fromEntity(userFavour.getFirst().getFavor());

        List<UserFavorDto> userFavorDtoList = userFavour
                .stream()
                .map(UserFavorDto::fromEntity)
                .toList();

        return new UserFavourGroupedByFavourDto(favorDto, userFavorDtoList);

    }
}
