package imt.framework.back.imtframeworkback.domain.usecases.favorites;
import imt.framework.back.imtframeworkback.data.services.FavoriteService;
import imt.framework.back.imtframeworkback.data.services.TokenService;
import imt.framework.back.imtframeworkback.domain.models.Dish;
import imt.framework.back.imtframeworkback.domain.models.Favorite;
import imt.framework.back.imtframeworkback.domain.models.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;

public class GetFavoriteUseCaseTest {
    @InjectMocks
    private GetFavoritesUseCase getFavoritesUseCase;

    @Mock
    private FavoriteService favoriteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getFavoritesShouldReturnAllFavorites() {
        try (MockedStatic<TokenService> mocked = Mockito.mockStatic(TokenService.class)) {
            mocked.when(() -> TokenService.isUserValid(0)).thenReturn(true);

            User user = User.builder().id(0).build();
            List<Favorite> favorites = List.of(Favorite.builder().id(0).user(user).build(), Favorite.builder().id(1).user(user).build());
            Mockito.when(favoriteService.findByUser(0)).thenReturn(favorites);

            List<Dish> res = getFavoritesUseCase.command(0);

            Assertions.assertThat(res).hasSize(2);
        }

    }
    @Test
    public void getDishesWithoutDishesShouldReturnEmpty() {
        try (MockedStatic<TokenService> mocked = Mockito.mockStatic(TokenService.class)) {
            mocked.when(() -> TokenService.isUserValid(0)).thenReturn(true);

            Mockito.when(favoriteService.findByUser(0)).thenReturn(List.of());

            List<Dish> res = getFavoritesUseCase.command(0);

            Assertions.assertThat(res).isEmpty();
        }
    }

}
