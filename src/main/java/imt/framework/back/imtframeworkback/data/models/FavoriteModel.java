package imt.framework.back.imtframeworkback.data.models;

import imt.framework.back.imtframeworkback.domain.models.Favorite;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "favorites")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class FavoriteModel {
    @Id
    @GeneratedValue
    @Column(name = "favorite_id")
    Integer favoriteId;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    UserModel user;
    @ManyToOne
    @JoinColumn(name = "dish_id", referencedColumnName = "dish_id")
    DishModel dish;

    public static FavoriteModel fromDomain(Favorite favorite) {
        return FavoriteModel.builder()
                .favoriteId(favorite.getId())
                .user(UserModel.fromDomain(favorite.getUser()))
                .dish(DishModel.fromDomain(favorite.getDish()))
                .build();
    }
}
