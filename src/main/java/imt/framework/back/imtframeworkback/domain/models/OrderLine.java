package imt.framework.back.imtframeworkback.domain.models;

import imt.framework.back.imtframeworkback.data.models.OrderLineModel;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class OrderLine {
    Integer id;
    Dish dish;
    Integer quantity;

    public static OrderLine fromData(OrderLineModel orderLineModel) {
        return OrderLine.builder()
                .id(orderLineModel.getOlId())
                .dish(Dish.fromData(orderLineModel.getDish()))
                .quantity(orderLineModel.getQuantity())
                .build();
    }
}
