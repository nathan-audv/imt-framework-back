package imt.framework.back.imtframeworkback.domain.usecases.dishes;

import imt.framework.back.imtframeworkback.data.services.DishService;
import imt.framework.back.imtframeworkback.domain.models.Dish;
import imt.framework.back.imtframeworkback.domain.requests.GetDishesReq;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Set;

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
    void getDishesShouldReturnAllDishes() {
        List<Dish> dishes = List.of(Dish.builder().id(0).build(), Dish.builder().id(1).build());
        GetDishesReq req = GetDishesReq.builder()
                .categoryFilter(null)
                .searchFilter(null)
                .build();

        Mockito.when(dishService.findAll()).thenReturn(dishes);

        Set<Dish> res = getDishesUseCase.command(req);

        Assertions.assertThat(res).hasSize(2);
    }

    @Test
    void getDishesWithoutDishesShouldReturnEmpty() {
        Mockito.when(dishService.findAll()).thenReturn(List.of());
        GetDishesReq req = GetDishesReq.builder()
                .categoryFilter(null)
                .searchFilter(null)
                .build();

        Set<Dish> res = getDishesUseCase.command(req);

        Assertions.assertThat(res).isEmpty();
    }

    @Test
    void getDishesShouldReturnVegeDishes() {
        Dish dish1 = Dish.builder().id(0).categories(List.of("Végé")).build();
        Dish dish2 = Dish.builder().id(1).categories(List.of("Other")).build();
        List<Dish> dishes = List.of(dish1, dish2);
        GetDishesReq req = GetDishesReq.builder()
                .categoryFilter(List.of("Végé"))
                .searchFilter(null)
                .build();

        Mockito.when(dishService.findAll()).thenReturn(dishes);

        Set<Dish> res = getDishesUseCase.command(req);

        Assertions.assertThat(res).containsExactly(dish1);
    }

    @Test
    void getDishesShouldReturnSearchMatchingDishes() {
        Dish dish1 = Dish.builder().id(0).title("test a").description("a").build();
        Dish dish2 = Dish.builder().id(1).title("b").description("test b").build();
        Dish dish3 = Dish.builder().id(2).title("test c").description("test c").build();
        Dish dish4 = Dish.builder().id(3).title("d").description("d").build();
        List<Dish> dishes = List.of(dish1, dish2, dish3, dish4);
        GetDishesReq req = GetDishesReq.builder()
                .categoryFilter(null)
                .searchFilter("test")
                .build();

        Mockito.when(dishService.findAll()).thenReturn(dishes);

        Set<Dish> res = getDishesUseCase.command(req);

        Assertions.assertThat(res).hasSize(3);
        Assertions.assertThat(res).contains(dish1, dish2, dish3);
    }

    @Test
    void getDishesNotMatchingFiltersShouldReturnEmpty() {
        Dish dish1 = Dish.builder().id(0).categories(List.of()).title("a").description("a").build();
        Dish dish2 = Dish.builder().id(2).categories(List.of("b")).title("b").description("b").build();
        List<Dish> dishes = List.of(dish1, dish2);
        GetDishesReq req = GetDishesReq.builder()
                .categoryFilter(List.of("c"))
                .searchFilter("c")
                .build();

        Mockito.when(dishService.findAll()).thenReturn(dishes);

        Set<Dish> res = getDishesUseCase.command(req);

        Assertions.assertThat(res).isEmpty();
    }

    @Test
    void getDishesOnlyMatchingSearchFilterShouldReturnEmpty() {
        Dish dish = Dish.builder().id(2).categories(List.of("b")).title("b").description("b").build();
        List<Dish> dishes = List.of(dish);
        GetDishesReq req = GetDishesReq.builder()
                .categoryFilter(List.of("c"))
                .searchFilter("b")
                .build();

        Mockito.when(dishService.findAll()).thenReturn(dishes);

        Set<Dish> res = getDishesUseCase.command(req);

        Assertions.assertThat(res).isEmpty();
    }

    @Test
    void getDishesOnlyMatchingCatFilterShouldReturnEmpty() {
        Dish dish = Dish.builder().id(2).categories(List.of("b")).title("b").description("b").build();
        List<Dish> dishes = List.of(dish);
        GetDishesReq req = GetDishesReq.builder()
                .categoryFilter(List.of("b"))
                .searchFilter("c")
                .build();

        Mockito.when(dishService.findAll()).thenReturn(dishes);

        Set<Dish> res = getDishesUseCase.command(req);

        Assertions.assertThat(res).isEmpty();
    }

    @Test
    void getDishesShouldReturnBothMatchingDishes() {
        Dish dish1 = Dish.builder().id(1).categories(List.of("b")).title("b").description("b").build();
        Dish dish2 = Dish.builder().id(2).categories(List.of("b")).title("c").description("c").build();
        List<Dish> dishes = List.of(dish1, dish2);
        GetDishesReq req = GetDishesReq.builder()
                .categoryFilter(List.of("b"))
                .searchFilter("b")
                .build();

        Mockito.when(dishService.findAll()).thenReturn(dishes);

        Set<Dish> res = getDishesUseCase.command(req);

        Assertions.assertThat(res).containsExactly(dish1);
    }
}
