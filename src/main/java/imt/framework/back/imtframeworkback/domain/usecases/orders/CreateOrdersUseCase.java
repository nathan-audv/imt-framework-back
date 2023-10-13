package imt.framework.back.imtframeworkback.domain.usecases.orders;

import imt.framework.back.imtframeworkback.core.errors.DishNotFoundException;
import imt.framework.back.imtframeworkback.core.errors.UserNotFoundException;
import imt.framework.back.imtframeworkback.core.utils.UseCase;
import imt.framework.back.imtframeworkback.data.services.DishService;
import imt.framework.back.imtframeworkback.data.services.OrderService;
import imt.framework.back.imtframeworkback.data.services.UserService;
import imt.framework.back.imtframeworkback.domain.models.Dish;
import imt.framework.back.imtframeworkback.domain.models.Order;
import imt.framework.back.imtframeworkback.domain.models.OrderLine;
import imt.framework.back.imtframeworkback.domain.models.User;
import imt.framework.back.imtframeworkback.domain.requests.CreateOrderReq;
import imt.framework.back.imtframeworkback.domain.requests.OrderLineReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CreateOrdersUseCase implements UseCase<CreateOrderReq, Order> {
    private final DishService dishService;
    private final UserService userService;
    private final OrderService orderService;

    @Override
    public Order command(CreateOrderReq createOrderReq) {
        List<OrderLine> orderLines = new ArrayList<>();

        Optional<User> user = userService.findById(createOrderReq.getUserId());
        if (user.isEmpty()) throw new UserNotFoundException(createOrderReq.getUserId().toString());

        for (OrderLineReq orderLineReq : createOrderReq.getOrderLines()) {
            Optional<Dish> dish = dishService.findById(orderLineReq.getDishId());
            if (dish.isEmpty()) throw new DishNotFoundException(orderLineReq.getDishId());
            else orderLines.add(OrderLine.builder().dish(dish.get()).quantity(orderLineReq.getQuantity()).build());
        }

        Order order = Order.builder()
                .user(user.get())
                .address(createOrderReq.getAddress())
                .orderLines(orderLines)
                .note(createOrderReq.getNote())
                .build();

        return orderService.save(order);
    }
}
