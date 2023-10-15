package imt.framework.back.imtframeworkback.data.repositories;

import imt.framework.back.imtframeworkback.data.models.FavoriteModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<FavoriteModel, Integer> {
    List<FavoriteModel> findAllByUser_UserId(Integer userId);

    Optional<FavoriteModel> findByUser_UserIdAndDish_DishId(Integer userId, Integer dishId);
}
