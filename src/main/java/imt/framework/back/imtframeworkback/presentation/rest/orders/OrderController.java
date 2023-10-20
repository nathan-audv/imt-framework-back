package imt.framework.back.imtframeworkback.presentation.rest.orders;

import imt.framework.back.imtframeworkback.domain.requests.CreateOrderReq;
import imt.framework.back.imtframeworkback.domain.requests.OrderLineReq;
import imt.framework.back.imtframeworkback.domain.results.OrderDetailsRes;
import imt.framework.back.imtframeworkback.domain.results.OrderRes;
import imt.framework.back.imtframeworkback.domain.usecases.orders.CreateOrdersUseCase;
import imt.framework.back.imtframeworkback.domain.usecases.orders.DeleteOrderUseCase;
import imt.framework.back.imtframeworkback.domain.usecases.orders.GetOrderDetailsUseCase;
import imt.framework.back.imtframeworkback.domain.usecases.orders.GetOrderHistoryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController implements OrderResources {
    private final CreateOrdersUseCase createOrdersUseCase;
    private final GetOrderHistoryUseCase getOrderHistoryUseCase;
    private final DeleteOrderUseCase deleteOrderUseCase;
    private final GetOrderDetailsUseCase getOrderDetailsUseCase;

    @Override
    public ResponseEntity<OrderRes> createOrder(String address, Integer userId, String note, List<OrderLineReq> orderLineReqs) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createOrdersUseCase.command(
                        CreateOrderReq.builder().orderLines(orderLineReqs).address(address).userId(userId).note(note).build()
                ));
    }

    @Override
    public List<OrderRes> getOrderHistory(Integer userId) {
        return getOrderHistoryUseCase.command(userId);
    }

    @Override
    public OrderDetailsRes getOrderDetails(Integer orderId) {
        return getOrderDetailsUseCase.command(orderId);
    }

    @Override
    public ResponseEntity<OrderRes> deleteOrder(Integer orderId) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(deleteOrderUseCase.command(orderId));
    }
}
