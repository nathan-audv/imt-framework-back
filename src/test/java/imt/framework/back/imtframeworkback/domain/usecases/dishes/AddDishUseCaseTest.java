package imt.framework.back.imtframeworkback.domain.usecases.dishes;

import imt.framework.back.imtframeworkback.data.services.DishService;
import imt.framework.back.imtframeworkback.domain.models.Dish;
import imt.framework.back.imtframeworkback.domain.requests.DishReq;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

public class AddDishUseCaseTest {

    @InjectMocks
    private AddDishUseCase addDishUseCase;

    @Mock
    private DishService dishService;

    @BeforeEach
    public void setUp ( ) {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void addDishShouldAdd ( ) {
        Dish dish = Dish.builder()
                .image("a")
                .title("b")
                .description("c")
                .price(0.0)
                .allergens(List.of("d"))
                .categories(List.of("e"))
                .build();
        Mockito.when(dishService.save(dish)).thenReturn(dish);

        DishReq dishReq = DishReq.builder()
                .image("a")
                .title("b")
                .description("c")
                .price(0.0)
                .allergens(List.of("d"))
                .categories(List.of("e"))
                .build();
        Dish res = addDishUseCase.command(dishReq);

        Assertions.assertThat(res).isEqualTo(dish);

    }
}
