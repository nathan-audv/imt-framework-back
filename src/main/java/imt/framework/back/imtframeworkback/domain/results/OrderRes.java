package imt.framework.back.imtframeworkback.domain.results;

import imt.framework.back.imtframeworkback.domain.models.Order;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class OrderRes {
    Integer id;
    Integer userId;
    Double price;
    Long date;

    public static OrderRes fromDomain(Order order){
        return OrderRes.builder()
                .id(order.getId())
                .userId(order.getUser().getId())
                .price(order.getPrice())
                .date(order.getDate())
                .build();
    }
}
