package imt.framework.back.imtframeworkback.presentation.rest.orders;

import imt.framework.back.imtframeworkback.domain.requests.OrderLineReq;
import imt.framework.back.imtframeworkback.domain.results.OrderRes;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1/orders")
public interface OrderResources {
    @PostMapping
    void createOrder(
            @RequestParam String address,
            @RequestParam Integer userId,
            @RequestParam String note,
            @RequestBody List<OrderLineReq> orderLines);

    @GetMapping
    List<OrderRes> getOrders(@RequestParam Integer userId);
}
