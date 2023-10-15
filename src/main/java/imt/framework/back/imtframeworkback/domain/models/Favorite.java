package imt.framework.back.imtframeworkback.domain.models;

import imt.framework.back.imtframeworkback.data.models.FavoriteModel;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Favorite {
    Integer id;
    User user;
    Dish dish;

    public static Favorite fromData(FavoriteModel favoriteModel) {
        return Favorite.builder()
                .id(favoriteModel.getFavoriteId())
                .user(User.fromData(favoriteModel.getUser()))
                .dish(Dish.fromData(favoriteModel.getDish()))
                .build();
    }
}
