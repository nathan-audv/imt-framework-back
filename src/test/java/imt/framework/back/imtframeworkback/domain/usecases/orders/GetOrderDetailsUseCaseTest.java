package imt.framework.back.imtframeworkback.domain.usecases.orders;

import imt.framework.back.imtframeworkback.core.errors.OrderNotFoundException;
import imt.framework.back.imtframeworkback.core.errors.UserNotValidException;
import imt.framework.back.imtframeworkback.data.services.OrderService;
import imt.framework.back.imtframeworkback.data.services.TokenService;
import imt.framework.back.imtframeworkback.domain.models.Dish;
import imt.framework.back.imtframeworkback.domain.models.Order;
import imt.framework.back.imtframeworkback.domain.models.OrderLine;
import imt.framework.back.imtframeworkback.domain.models.User;
import imt.framework.back.imtframeworkback.domain.results.OrderDetailsRes;
import imt.framework.back.imtframeworkback.domain.results.OrderRes;
import org.assertj.core.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

public class GetOrderDetailsUseCaseTest {
    @InjectMocks
    private GetOrderDetailsUseCase getOrderDetailsUseCase;

    @Mock
    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void notValidUserShouldThrowUserNotValidException(){
        Integer userId = 0;
        User user = User.builder().id(userId).build();
        Integer request = 0;
        Mockito.when(orderService.findById(request)).thenThrow(UserNotValidException.class);
        assertThrows(UserNotValidException.class, () -> getOrderDetailsUseCase.command(request));
    }

    @Test
    public void orderNotFoundShouldThrowException(){
        Integer request = 0;
        Mockito.when(orderService.findById(request)).thenThrow(OrderNotFoundException.class);
        assertThrows(OrderNotFoundException.class, () -> getOrderDetailsUseCase.command(request));
    }

    @Test
    public void getOrderDetailsShouldReturnOrderDetails(){
        try (MockedStatic<TokenService> mocked = Mockito.mockStatic(TokenService.class)) {
            Integer userId = 0;
            User user = User.builder().id(userId).build();
            mocked.when(() -> TokenService.isUserValid(userId)).thenReturn(true);
            Integer orderLineId = 0;
            Integer dishId = 0;
            String dishTitle = "Carbonara de test";
            Dish dish = Dish.builder().id(dishId).title(dishTitle).build();
            List<OrderLine> orderLines = List.of(OrderLine.builder().id(orderLineId).dish(dish).build());
            Integer request = 0;
            List<String> orderLinesString = orderLines.stream().map(orderLine -> orderLine.getDish().getTitle()).toList();
            Order order = Order.builder().user(user).orderLines(orderLines).build();
            OrderDetailsRes expected = OrderDetailsRes.builder().orderLines(orderLinesString).build();

            Mockito.when(orderService.findById(userId)).thenReturn(Optional.of(order));
            OrderDetailsRes res = getOrderDetailsUseCase.command(userId);
            Assertions.assertThat(res).isEqualTo(expected);
        }

    }
}
