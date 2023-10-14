package imt.framework.back.imtframeworkback.data.services;

import imt.framework.back.imtframeworkback.domain.models.Dish;
import imt.framework.back.imtframeworkback.domain.models.Favorite;
import imt.framework.back.imtframeworkback.domain.models.User;

import java.util.List;
import java.util.Optional;

public interface FavoriteService {
    Favorite save(Favorite favorite);
    List<Favorite> findByUser(Integer userId);
    Optional<Favorite> findByUserAndDish(Integer userId, Integer dishId);
    void remove(Favorite favorite);
}
