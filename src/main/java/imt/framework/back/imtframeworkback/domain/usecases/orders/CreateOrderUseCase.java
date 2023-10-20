package imt.framework.back.imtframeworkback.domain.usecases.orders;

import imt.framework.back.imtframeworkback.core.errors.DishNotFoundException;
import imt.framework.back.imtframeworkback.core.errors.UserHasNotEnoughMoneyException;
import imt.framework.back.imtframeworkback.core.errors.UserNotFoundException;
import imt.framework.back.imtframeworkback.core.errors.UserNotValidException;
import imt.framework.back.imtframeworkback.core.utils.UseCase;
import imt.framework.back.imtframeworkback.data.services.DishService;
import imt.framework.back.imtframeworkback.data.services.OrderService;
import imt.framework.back.imtframeworkback.data.services.TokenService;
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
public class CreateOrderUseCase implements UseCase<CreateOrderReq, OrderRes> {
    private final DishService dishService;
    private final UserService userService;
    private final OrderService orderService;

    @Override
    public OrderRes command(CreateOrderReq request) {
        if (!TokenService.isUserValid(request.getUserId())) throw new UserNotValidException();

        List<OrderLine> orderLines = new ArrayList<>();
        String address = request.getAddress();
        double cost = 0.0;

        User user = userService.findById(request.getUserId()).orElseThrow(() -> new UserNotFoundException(request.getUserId().toString()));

        for (OrderLineReq orderLineReq : request.getOrderLines()) {
            Dish dish = dishService.findById(orderLineReq.getDishId()).orElseThrow(() -> new DishNotFoundException(orderLineReq.getDishId()));
            cost += dish.getPrice() * orderLineReq.getQuantity();
            orderLines.add(OrderLine.builder().dish(dish).quantity(orderLineReq.getQuantity()).build());
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
