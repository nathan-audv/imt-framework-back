package imt.framework.back.imtframeworkback.presentation.rest.orders;

import imt.framework.back.imtframeworkback.domain.requests.CreateOrderReq;
import imt.framework.back.imtframeworkback.domain.requests.OrderLineReq;
import imt.framework.back.imtframeworkback.domain.usecases.orders.CreateOrdersUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController implements OrderResources {
    private final CreateOrdersUseCase createOrdersUseCase;

    @Override
    public void createOrder(String address, Integer userId, String note, List<OrderLineReq> orderLineReqs) {
        createOrdersUseCase.command(
                CreateOrderReq.builder().orderLines(orderLineReqs).address(address).userId(userId).note(note).build()
        );
    }
}
