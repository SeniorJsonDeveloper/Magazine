package web.magazine.repository;

import web.magazine.entities.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    List<Order> fetchAll();
    Optional<Order> findById(Long id);
    Order createOrder(Order order);
    Order updateOrder(Order order);
    void deleteOrderById(Long id);
    void deleteAllOrders(List<Long> ids);
}
