package imt.framework.back.imtframeworkback.domain.usecases.favorites;

import imt.framework.back.imtframeworkback.core.errors.DishNotFoundException;
import imt.framework.back.imtframeworkback.core.errors.UserNotFoundException;
import imt.framework.back.imtframeworkback.core.utils.UseCase;
import imt.framework.back.imtframeworkback.data.services.DishService;
import imt.framework.back.imtframeworkback.data.services.FavoriteService;
import imt.framework.back.imtframeworkback.data.services.UserService;
import imt.framework.back.imtframeworkback.domain.models.Dish;
import imt.framework.back.imtframeworkback.domain.models.Favorite;
import imt.framework.back.imtframeworkback.domain.models.User;
import imt.framework.back.imtframeworkback.domain.requests.UpdateFavoritesReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UpdateFavoritesUseCase implements UseCase<UpdateFavoritesReq, Void> {
    private final FavoriteService favoriteService;
    private final UserService userService;
    private final DishService dishService;

    @Override
    public Void command(UpdateFavoritesReq updateFavoritesReq) {
        Integer userId = updateFavoritesReq.getUserId();
        Integer dishId = updateFavoritesReq.getDishId();

        Optional<User> user = userService.findById(userId);
        if (user.isEmpty()) throw new UserNotFoundException(userId.toString());

        Optional<Dish> dish = dishService.findById(dishId);
        if (dish.isEmpty()) throw new DishNotFoundException(dishId);

        Optional<Favorite> favorite = favoriteService.findByUserAndDish(userId, dishId);
        if (favorite.isEmpty()) {
            favoriteService.save(Favorite.builder()
                    .user(user.get())
                    .dish(dish.get())
                    .build());
        } else {
            favoriteService.remove(favorite.get());
        }
        return null;
    }
}
