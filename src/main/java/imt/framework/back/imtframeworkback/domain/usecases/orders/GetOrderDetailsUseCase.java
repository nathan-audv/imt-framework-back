package imt.framework.back.imtframeworkback.domain.usecases.orders;

import imt.framework.back.imtframeworkback.core.errors.OrderNotFoundException;
import imt.framework.back.imtframeworkback.core.errors.UserNotValidException;
import imt.framework.back.imtframeworkback.core.utils.UseCase;
import imt.framework.back.imtframeworkback.data.services.OrderService;
import imt.framework.back.imtframeworkback.data.services.TokenService;
import imt.framework.back.imtframeworkback.domain.models.Order;
import imt.framework.back.imtframeworkback.domain.results.OrderDetailsRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetOrderDetailsUseCase implements UseCase<Integer, OrderDetailsRes> {
    private final OrderService orderService;

    @Override
    public OrderDetailsRes command(Integer request) {
        Order order = orderService.findById(request).orElseThrow(() -> new OrderNotFoundException(request));

        if (!TokenService.isUserValid(order.getUser().getId())) throw new UserNotValidException();

        return OrderDetailsRes.fromDomain(order);


    }
}
