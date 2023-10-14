package imt.framework.back.imtframeworkback.data.services;

import imt.framework.back.imtframeworkback.domain.models.Order;

import java.util.List;

public interface OrderService {
    Order save(Order order);
    List<Order> findByUser(Integer userId);
}
