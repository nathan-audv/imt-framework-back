package imt.framework.back.imtframeworkback.data.models;

import imt.framework.back.imtframeworkback.domain.models.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class OrderModel {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    Integer orderId;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    UserModel user;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) @JoinColumn(name = "order_lines_id")
    List<OrderLineModel> orderLines;
    String address;
    String note;

    public static OrderModel fromDomain(Order order) {
        return OrderModel.builder()
                .orderId(order.getId())
                .user(UserModel.fromDomain(order.getUser()))
                .orderLines(order.getOrderLines().stream().map(OrderLineModel::fromDomain).toList())
                .address(order.getAddress())
                .note(order.getNote())
                .build();
    }
}
