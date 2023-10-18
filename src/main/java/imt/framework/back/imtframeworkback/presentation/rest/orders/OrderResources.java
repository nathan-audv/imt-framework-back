package imt.framework.back.imtframeworkback.presentation.rest.orders;

import imt.framework.back.imtframeworkback.domain.models.Order;
import imt.framework.back.imtframeworkback.domain.requests.OrderLineReq;
import imt.framework.back.imtframeworkback.domain.results.OrderDetailsRes;
import imt.framework.back.imtframeworkback.domain.results.OrderRes;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1/orders")
public interface OrderResources {
    @PostMapping
    OrderRes createOrder(
            @RequestParam(required = false) String address,
            @RequestParam Integer userId,
            @RequestParam(required = false) String note,
            @RequestBody List<OrderLineReq> orderLines);

    @GetMapping
    List<OrderRes> getOrderHistory(@RequestParam Integer userId);

    @GetMapping("/details")
    OrderDetailsRes getOrderDetails(@RequestParam Integer orderId);

    @DeleteMapping
    OrderRes deleteOrder(@RequestParam Integer orderId);
}
