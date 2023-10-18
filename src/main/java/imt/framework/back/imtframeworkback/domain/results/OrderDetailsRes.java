package imt.framework.back.imtframeworkback.domain.results;

import imt.framework.back.imtframeworkback.domain.models.Order;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class OrderDetailsRes {
    Integer id;
    Double price;
    Long date;
    List<String> orderLines;

    public static OrderDetailsRes fromDomain(Order order) {
        return OrderDetailsRes.builder()
                .id(order.getId())
                .price(order.getPrice())
                .date(order.getDate())
                .orderLines(order.getOrderLines().stream().map(orderLine -> orderLine.getDish().getTitle()).toList())
                .build();
    }
}
