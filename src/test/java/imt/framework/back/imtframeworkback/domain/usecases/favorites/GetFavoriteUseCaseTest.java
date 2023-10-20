package imt.framework.back.imtframeworkback.domain.usecases.favorites;

import imt.framework.back.imtframeworkback.data.services.DishService;
import imt.framework.back.imtframeworkback.data.services.FavoriteService;
import imt.framework.back.imtframeworkback.data.services.UserService;
import imt.framework.back.imtframeworkback.domain.models.Dish;
import imt.framework.back.imtframeworkback.domain.models.Favorite;
import imt.framework.back.imtframeworkback.domain.models.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

public class GetFavoriteUseCaseTest {
    @InjectMocks
    private GetFavoritesUseCase getFavoritesUseCase;

    @Mock
    private FavoriteService favoriteService;
    @Mock
    private UserService userService;
    @Mock
    private DishService dishService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getFavoritesShouldReturnAllFavorites() {
        User user = User.builder().id(0).build();
        List<Favorite> favorites = List.of(Favorite.builder().id(0).user(user).build(),Favorite.builder().id(1).user(user).build());
        Mockito.when(favoriteService.findByUser(0)).thenReturn(favorites);

        List<Dish> res = getFavoritesUseCase.command(0);

        Assertions.assertThat(res).hasSize(2);

    }
    @Test
    public void getDishesWithoutDishesShouldReturnEmpty() {
        User user = User.builder().id(0).build();
        Mockito.when(favoriteService.findByUser(0)).thenReturn(List.of());

        List<Dish> res = getFavoritesUseCase.command(0);

        Assertions.assertThat(res).isEmpty();
    }

}
