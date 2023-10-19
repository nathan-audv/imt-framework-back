package imt.framework.back.imtframeworkback.domain.usecases.dishes;

import imt.framework.back.imtframeworkback.data.services.DishService;
import imt.framework.back.imtframeworkback.domain.models.Dish;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

public class GetDishesUseCaseTest {
    @InjectMocks
    private GetDishesUseCase getDishesUseCase;

    @Mock
    private DishService dishService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getDishesShouldReturnAllDishes() {
        List<Dish> dishes = List.of(Dish.builder().id(0).build(), Dish.builder().id(1).build());
        Mockito.when(dishService.findAll()).thenReturn(dishes);

        List<Dish> res = getDishesUseCase.command(null);

        Assertions.assertThat(res).hasSize(2);
    }

    @Test
    public void getDishesWithoutDishesShouldReturnEmpty() {
        Mockito.when(dishService.findAll()).thenReturn(List.of());

        List<Dish> res = getDishesUseCase.command(null);

        Assertions.assertThat(res).isEmpty();
    }
}
