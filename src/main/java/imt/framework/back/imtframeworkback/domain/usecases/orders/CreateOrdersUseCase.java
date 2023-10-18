package imt.framework.back.imtframeworkback.domain.usecases.orders;

import imt.framework.back.imtframeworkback.core.errors.DishNotFoundException;
import imt.framework.back.imtframeworkback.core.errors.UserHasNotEnoughMoneyException;
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
import imt.framework.back.imtframeworkback.domain.results.OrderRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CreateOrdersUseCase implements UseCase<CreateOrderReq, OrderRes> {
    private final DishService dishService;
    private final UserService userService;
    private final OrderService orderService;

    @Override
    public OrderRes command(CreateOrderReq request) {
        List<OrderLine> orderLines = new ArrayList<>();
        String address = request.getAddress();
        double cost = 0.0;

        Optional<User> optUser = userService.findById(request.getUserId());
        if (optUser.isEmpty()) throw new UserNotFoundException(request.getUserId().toString());
        User user = optUser.get();

        for (OrderLineReq orderLineReq : request.getOrderLines()) {
            Optional<Dish> dish = dishService.findById(orderLineReq.getDishId());
            if (dish.isEmpty()) throw new DishNotFoundException(orderLineReq.getDishId());
            Dish tmpDish = dish.get();
            cost += tmpDish.getPrice() * orderLineReq.getQuantity();
            orderLines.add(OrderLine.builder().dish(tmpDish).quantity(orderLineReq.getQuantity()).build());
        }

        if (cost > user.getBalance()) throw new UserHasNotEnoughMoneyException(user.getId());
        user = user.toBuilder().balance(user.getBalance() - cost).build();
        userService.save(user);

        Order order = Order.builder()
                .user(user)
                .address(address)
                .orderLines(orderLines)
                .price(cost)
                .date(Instant.now().toEpochMilli())
                .note(request.getNote())
                .build();

        return OrderRes.fromDomain(orderService.save(order));
    }
}
