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
    private Integer orderId;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserModel user;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private List<OrderLineModel> orderLines;
    private Double price;
    private Long date;
    private String address;
    private String note;

    public static OrderModel fromDomain(Order order) {
        return OrderModel.builder()
                .orderId(order.getId())
                .user(UserModel.fromDomain(order.getUser()))
                .orderLines(order.getOrderLines().stream().map(OrderLineModel::fromDomain).toList())
                .price(order.getPrice())
                .date(order.getDate())
                .address(order.getAddress())
                .note(order.getNote())
                .build();
    }
}
