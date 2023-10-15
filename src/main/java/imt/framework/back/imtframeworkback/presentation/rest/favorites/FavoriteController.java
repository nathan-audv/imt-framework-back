package imt.framework.back.imtframeworkback.presentation.rest.favorites;

import imt.framework.back.imtframeworkback.domain.models.Dish;
import imt.framework.back.imtframeworkback.domain.requests.UpdateFavoritesReq;
import imt.framework.back.imtframeworkback.domain.usecases.favorites.GetFavoritesUseCase;
import imt.framework.back.imtframeworkback.domain.usecases.favorites.UpdateFavoritesUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FavoriteController implements FavoriteResources {
    private final UpdateFavoritesUseCase updateFavoritesUseCase;
    private final GetFavoritesUseCase getFavoritesUseCase;

    @Override
    public List<Dish> getFavorites(Integer userId) {
        return getFavoritesUseCase.command(userId);
    }

    @Override
    public void updateFavorites(Integer userId, Integer dishId) {
        updateFavoritesUseCase.command(UpdateFavoritesReq.builder()
                .userId(userId)
                .dishId(dishId)
                .build());
    }

}
