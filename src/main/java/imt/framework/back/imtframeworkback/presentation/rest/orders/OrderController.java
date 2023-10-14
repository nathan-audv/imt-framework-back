package imt.framework.back.imtframeworkback.presentation.rest.orders;

import imt.framework.back.imtframeworkback.domain.requests.CreateOrderReq;
import imt.framework.back.imtframeworkback.domain.requests.OrderLineReq;
import imt.framework.back.imtframeworkback.domain.results.OrderRes;
import imt.framework.back.imtframeworkback.domain.usecases.orders.CreateOrdersUseCase;
import imt.framework.back.imtframeworkback.domain.usecases.orders.GetOrdersUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController implements OrderResources {
    private final CreateOrdersUseCase createOrdersUseCase;
    private final GetOrdersUseCase getOrdersUseCase;

    @Override
    public OrderRes createOrder(String address, Integer userId, String note, List<OrderLineReq> orderLineReqs) {
        return createOrdersUseCase.command(
                CreateOrderReq.builder().orderLines(orderLineReqs).address(address).userId(userId).note(note).build()
        );
    }

    @Override
    public List<OrderRes> getOrders(Integer userId) {
        return getOrdersUseCase.command(userId);
    }
}
