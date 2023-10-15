package imt.framework.back.imtframeworkback.presentation.rest.orders;

import imt.framework.back.imtframeworkback.domain.requests.CreateOrderReq;
import imt.framework.back.imtframeworkback.domain.requests.OrderLineReq;
import imt.framework.back.imtframeworkback.domain.results.OrderRes;
import imt.framework.back.imtframeworkback.domain.usecases.orders.CreateOrdersUseCase;
import imt.framework.back.imtframeworkback.domain.usecases.orders.GetCurrentOrdersUseCase;
import imt.framework.back.imtframeworkback.domain.usecases.orders.GetOrderHistoryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class OrderController implements OrderResources {
    private final CreateOrdersUseCase createOrdersUseCase;
    private final GetOrderHistoryUseCase getOrderHistoryUseCase;
    private final GetCurrentOrdersUseCase getCurrentOrdersUseCase;

    @Override
    public OrderRes createOrder(Optional<String> address, Integer userId, String note, List<OrderLineReq> orderLineReqs) {
        return createOrdersUseCase.command(
                CreateOrderReq.builder().orderLines(orderLineReqs).address(address).userId(userId).note(note).build()
        );
    }

    @Override
    public List<OrderRes> getOrderHistory(Integer userId) {
        return getOrderHistoryUseCase.command(userId);
    }

    @Override
    public List<OrderRes> getCurrentOrders(Integer userId) {
        return getCurrentOrdersUseCase.command(userId);
    }
}
