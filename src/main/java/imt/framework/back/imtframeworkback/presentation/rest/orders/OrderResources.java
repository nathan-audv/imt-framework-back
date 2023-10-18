package imt.framework.back.imtframeworkback.presentation.rest.orders;

import imt.framework.back.imtframeworkback.domain.requests.OrderLineReq;
import imt.framework.back.imtframeworkback.domain.results.OrderRes;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @DeleteMapping
    OrderRes deleteOrder(@RequestParam Integer orderId);
}
