package imt.framework.back.imtframeworkback.domain.usecases.orders;

import imt.framework.back.imtframeworkback.core.errors.DishNotFoundException;
import imt.framework.back.imtframeworkback.core.errors.UserNotFoundException;
import imt.framework.back.imtframeworkback.core.errors.UserNotValidException;
import imt.framework.back.imtframeworkback.data.services.DishService;
import imt.framework.back.imtframeworkback.data.services.OrderService;
import imt.framework.back.imtframeworkback.data.services.TokenService;
import imt.framework.back.imtframeworkback.data.services.UserService;
import imt.framework.back.imtframeworkback.domain.models.Dish;
import imt.framework.back.imtframeworkback.domain.models.Order;
import imt.framework.back.imtframeworkback.domain.models.OrderLine;
import imt.framework.back.imtframeworkback.domain.models.User;
import imt.framework.back.imtframeworkback.domain.requests.CreateOrderReq;
import imt.framework.back.imtframeworkback.domain.requests.OrderLineReq;
import imt.framework.back.imtframeworkback.domain.results.OrderRes;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

public class CreateOrderUseCaseTest {
    @InjectMocks
    private CreateOrderUseCase createOrderUseCase;

    @Mock
    private DishService dishService;

    @Mock
    private UserService userService;

    @Mock
    private OrderService orderService;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createOrderShouldReturnOrder() {
        Integer userId = 0;
        User user = User.builder().id(userId).balance(200.0).build();
        Integer dishId = 0;
        Dish dish = Dish.builder().id(dishId).price(10.0).build();
        Order order = Order.builder()
                .id(0)
                .user(user.toBuilder().balance(190.0).build())
                .orderLines(List.of(OrderLine.builder().dish(dish).quantity(1).build()))
                .price(10.0)
                .address("IMT Nord Europe")
                .date(Instant.now().toEpochMilli())
                .build();

        CreateOrderReq request = CreateOrderReq.builder()
                .orderLines(List.of(OrderLineReq.builder().dishId(dishId).quantity(1).build()))
                .userId(userId)
                .address("IMT Nord Europe")
                .build();

        try (MockedStatic<TokenService> mocked = Mockito.mockStatic(TokenService.class)) {
            mocked.when(() -> TokenService.isUserValid(userId)).thenReturn(true);
            Mockito.when(userService.findById(userId)).thenReturn(Optional.of(user));
            Mockito.when(dishService.findById(dishId)).thenReturn(Optional.of(dish));
            Mockito.when(orderService.save(any())).thenReturn(order);


            OrderRes res = createOrderUseCase.command(request);

            Assertions.assertThat(res.getUserId()).isEqualTo(userId);
            Assertions.assertThat(res.isFinished()).isFalse();
            Assertions.assertThat(res.getPrice()).isEqualTo(10.0);
        }
    }

    @Test
    public void createOrderWithOtherUserIdShouldThrowsUserNotValidException() {
        Integer userId = 0;
        Integer dishId = 0;
        CreateOrderReq request = CreateOrderReq.builder()
                .orderLines(List.of(OrderLineReq.builder().dishId(dishId).quantity(1).build()))
                .userId(userId)
                .address("IMT Nord Europe")
                .build();

        try (MockedStatic<TokenService> mocked = Mockito.mockStatic(TokenService.class)) {
            mocked.when(() -> TokenService.isUserValid(userId)).thenReturn(false);

            assertThrows(UserNotValidException.class, () -> createOrderUseCase.command(request));

            Mockito.verify(orderService, Mockito.times(0)).save(any());
        }
    }

    @Test
    public void createOrderUserNotFoundShouldThrowsUserNotFoundException() {
        Integer userId = 0;
        Integer dishId = 0;

        CreateOrderReq request = CreateOrderReq.builder()
                .orderLines(List.of(OrderLineReq.builder().dishId(dishId).quantity(1).build()))
                .userId(userId)
                .address("IMT Nord Europe")
                .build();

        try (MockedStatic<TokenService> mocked = Mockito.mockStatic(TokenService.class)) {
            mocked.when(() -> TokenService.isUserValid(userId)).thenReturn(true);
            Mockito.when(userService.findById(userId)).thenReturn(Optional.empty());

            assertThrows(UserNotFoundException.class, () -> createOrderUseCase.command(request));

            Mockito.verify(orderService, Mockito.times(0)).save(any());
        }
    }

    @Test
    public void createOrderWithoutDishShouldReturnOrder() {
        Integer userId = 0;
        User user = User.builder().id(userId).balance(200.0).build();
        Integer dishId = 0;

        CreateOrderReq request = CreateOrderReq.builder()
                .orderLines(List.of(OrderLineReq.builder().dishId(dishId).quantity(1).build()))
                .userId(userId)
                .address("IMT Nord Europe")
                .build();

        try (MockedStatic<TokenService> mocked = Mockito.mockStatic(TokenService.class)) {
            mocked.when(() -> TokenService.isUserValid(userId)).thenReturn(true);
            Mockito.when(userService.findById(userId)).thenReturn(Optional.of(user));
            Mockito.when(dishService.findById(dishId)).thenReturn(Optional.empty());

            assertThrows(DishNotFoundException.class, () -> createOrderUseCase.command(request));

            Mockito.verify(orderService, Mockito.times(0)).save(any());
        }
    }
}
