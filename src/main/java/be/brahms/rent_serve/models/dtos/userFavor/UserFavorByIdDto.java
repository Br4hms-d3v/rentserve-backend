package be.brahms.rent_serve.models.dtos.userFavor;

import be.brahms.rent_serve.models.dtos.favor.FavorDto;
import be.brahms.rent_serve.models.dtos.user.UserDto;
import be.brahms.rent_serve.models.entities.Picture;
import be.brahms.rent_serve.models.entities.UserFavor;

import java.math.BigDecimal;
import java.util.List;

public record UserFavorByIdDto(
        long id,
        String descriptionFavor,
        BigDecimal priceHourFavor,
        Boolean isAvailable,
        List<String> pictures,
        UserDto user,
        FavorDto favor
) {
    public static UserFavorByIdDto fromEntity(UserFavor userFavor) {
        List<String> allpictures = userFavor.getPictures()
                .stream()
                .map(Picture::getNamePicture)
                .toList();

        return new UserFavorByIdDto(
                userFavor.getId(),
                userFavor.getDescriptionFavor(),
                userFavor.getPriceHourFavor(),
                userFavor.isAvailable(),
                allpictures,
                UserDto.fromEntity(userFavor.getUser()),
                FavorDto.fromEntity(userFavor.getFavor())
        );
    }
}
