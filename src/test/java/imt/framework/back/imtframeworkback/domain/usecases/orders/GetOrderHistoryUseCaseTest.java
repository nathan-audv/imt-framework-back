package imt.framework.back.imtframeworkback.domain.usecases.orders;

import imt.framework.back.imtframeworkback.core.errors.UserNotValidException;
import imt.framework.back.imtframeworkback.data.services.OrderService;
import imt.framework.back.imtframeworkback.data.services.TokenService;
import imt.framework.back.imtframeworkback.domain.models.Order;
import imt.framework.back.imtframeworkback.domain.models.User;
import imt.framework.back.imtframeworkback.domain.results.OrderRes;
import org.assertj.core.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;

public class GetOrderHistoryUseCaseTest {
    @InjectMocks
    private GetOrderHistoryUseCase getOrderHistoryUseCase;

    @Mock
    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void notValidUserShouldThrowUserNotValidException(){
        Integer userId = 0;
        Integer request = 0;
        Mockito.when(orderService.findById(request)).thenThrow(UserNotValidException.class);
        assertThrows(UserNotValidException.class, () -> getOrderHistoryUseCase.command(request));
    }

    @Test
    public void getOrderHistoryShouldReturnHistory() {
        try (MockedStatic<TokenService> mocked = Mockito.mockStatic(TokenService.class)) {
            Integer userId = 0;
            User user = User.builder().id(userId).build();
            Integer orderId = 0;
            mocked.when(() -> TokenService.isUserValid(userId)).thenReturn(true);
            List<Order> orders = List.of(Order.builder().id(orderId).user(user).build(),Order.builder().id(orderId).user(user).price(2.0).build());
            List<OrderRes> expected = List.of(OrderRes.builder().id(orderId).userId(userId).build(),OrderRes.builder().id(orderId).price(2.0).userId(userId).build());
            Mockito.when(orderService.findByUser(userId)).thenReturn(orders);

            List<OrderRes> res = getOrderHistoryUseCase.command(userId);

            Assertions.assertThat(res).containsExactlyElementsOf(expected);
        }

    }
}
