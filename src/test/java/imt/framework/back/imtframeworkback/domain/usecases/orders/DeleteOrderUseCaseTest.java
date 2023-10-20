package imt.framework.back.imtframeworkback.domain.usecases.orders;

import imt.framework.back.imtframeworkback.core.errors.OrderNotFoundException;
import imt.framework.back.imtframeworkback.core.errors.UserNotValidException;
import imt.framework.back.imtframeworkback.data.services.*;
import imt.framework.back.imtframeworkback.domain.models.Dish;
import imt.framework.back.imtframeworkback.domain.models.Order;
import imt.framework.back.imtframeworkback.domain.models.OrderLine;
import imt.framework.back.imtframeworkback.domain.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;


public class DeleteOrderUseCaseTest {

    @InjectMocks
    private DeleteOrderUseCase deleteOrderUseCase;

    @Mock
    private OrderService orderService;

    @Mock
    private UserService userService;

    @Mock
    private DishService dishService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deleteOrderWhileOrderDoesNotExistShouldThrowErrorOrderNotFoundException(){
        Integer request = 0;
        Mockito.when(orderService.findById(request)).thenThrow(OrderNotFoundException.class);
        Assertions.assertThrows(OrderNotFoundException.class, () -> deleteOrderUseCase.command(request));
    }


    @Test
    public void deleteOrderShouldEffectivelyDeleteOrder(){
        try (MockedStatic<TokenService> mocked = Mockito.mockStatic(TokenService.class)) {
            Integer userId = 0;
            User user = User.builder().id(userId).build();
            mocked.when(() -> TokenService.isUserValid(userId)).thenReturn(true);
            Integer dishId = 0;
            Dish dish = Dish.builder().id(dishId).build();
            Integer orderLineId = 0;
            List<OrderLine> orderLine = List.of(OrderLine.builder().id(orderLineId).dish(dish).build());
            Integer request = 0;
            Order order = Order.builder().id(request).user(user).orderLines(orderLine).build();

            Mockito.when(userService.findById(userId)).thenReturn(Optional.of(user));
            Mockito.when(orderService.findById(request)).thenReturn(Optional.of(order));
            Mockito.doNothing().when(orderService).delete(order);

            deleteOrderUseCase.command(request);

            Mockito.verify(orderService, Mockito.times(1)).delete(order);
        }
    }


    @Test
    public void notValidUserShouldThrowUserNotValidException(){
        Integer userId = 0;
        User user = User.builder().id(userId).build();
        Integer request = 0;
        Order order = Order.builder().id(request).user(user).build();

        Mockito.when(userService.findById(userId)).thenReturn(Optional.of(user));
        Mockito.when(orderService.findById(request)).thenThrow(UserNotValidException.class);
        Assertions.assertThrows(UserNotValidException.class, () -> deleteOrderUseCase.command(request));

    }
}
