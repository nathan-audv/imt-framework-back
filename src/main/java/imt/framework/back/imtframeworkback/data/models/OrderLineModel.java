package imt.framework.back.imtframeworkback.data.models;

import imt.framework.back.imtframeworkback.domain.models.OrderLine;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "order_lines")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class OrderLineModel {
    @Id
    @GeneratedValue
    @Column(name = "ol_id")
    Integer olId;
    @ManyToOne
    @JoinColumn(name = "dish_id", referencedColumnName = "dish_id")
    DishModel dish;
    Integer quantity;

    public static OrderLineModel fromDomain(OrderLine orderLine) {
        return OrderLineModel.builder()
                .olId(orderLine.getId())
                .dish(DishModel.fromDomain(orderLine.getDish()))
                .quantity(orderLine.getQuantity())
                .build();
    }
}
