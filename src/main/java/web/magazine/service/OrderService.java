package web.magazine.service;

import org.springframework.data.util.Lazy;
import web.magazine.entities.Order;
import web.magazine.filter.OrderFilter;

import java.util.List;

public interface OrderService {
    List<Order> withFilter(OrderFilter filter);
    List<Order> getAllOrders();
    Order findById(Long id);
    Order createOrder(Order order);
    Order updateOrder(Order order);
    void deleteById(Long id);
    void deleteAllOrders(List<Long> ids);

}
