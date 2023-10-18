package imt.framework.back.imtframeworkback.data.services;

import imt.framework.back.imtframeworkback.domain.models.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order save(Order order);

    void delete(Order order);

    List<Order> findByUser(Integer userId);
    Optional<Order> findById(Integer orderId);
}
