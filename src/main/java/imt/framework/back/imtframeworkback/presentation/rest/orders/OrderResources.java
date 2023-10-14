package imt.framework.back.imtframeworkback.presentation.rest.orders;

import imt.framework.back.imtframeworkback.domain.models.Order;
import imt.framework.back.imtframeworkback.domain.requests.OrderLineReq;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/v1/orders")
public interface OrderResources {
    @PostMapping
    void createOrder(
            @RequestParam String address,
            @RequestParam Integer userId,
            @RequestParam String note,
            @RequestBody List<OrderLineReq> orderLines);
}
