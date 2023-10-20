package imt.framework.back.imtframeworkback.domain.usecases.favorites;

import imt.framework.back.imtframeworkback.core.errors.UserNotValidException;
import imt.framework.back.imtframeworkback.core.utils.UseCase;
import imt.framework.back.imtframeworkback.data.services.FavoriteService;
import imt.framework.back.imtframeworkback.data.services.TokenService;
import imt.framework.back.imtframeworkback.domain.models.Dish;
import imt.framework.back.imtframeworkback.domain.models.Favorite;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetFavoritesUseCase implements UseCase<Integer, List<Dish>> {
    private final FavoriteService favoriteService;

    @Override
    public List<Dish> command(Integer request) {
        if (!TokenService.isUserValid(request)) throw new UserNotValidException();

        return favoriteService.findByUser(request).stream().map(Favorite::getDish).toList();
    }
}
