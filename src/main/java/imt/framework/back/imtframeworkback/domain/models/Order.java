package imt.framework.back.imtframeworkback.domain.models;

import imt.framework.back.imtframeworkback.data.models.OrderModel;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class Order {
    Integer id;
    User user;
    String address;
    List<OrderLine> orderLines;
    String note;

    public static Order fromData(OrderModel orderModel) {
        return Order.builder()
                .id(orderModel.getOrderId())
                .user(User.fromData(orderModel.getUser()))
                .address(orderModel.getAddress())
                .orderLines(orderModel.getOrderLines().stream().map(OrderLine::fromData).toList())
                .note(orderModel.getNote())
                .build();
    }
}
