package web.magazine.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import web.magazine.entities.Order;
import web.magazine.filter.OrderFilter;
import web.magazine.repository.OrderRepository;
import web.magazine.service.OrderService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public List<Order> withFilter(OrderFilter filter) {
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.fetchAll();
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(()->new RuntimeException(
                        String.format("Order with \"%s\" not found!",id))
                );
    }

    @Override
    public Order createOrder(Order order) {
        return orderRepository.createOrder(order);
    }

    @Override
    public Order updateOrder(Order order) {
        return orderRepository.updateOrder(order);
    }

    @Override
    public void deleteById(Long id) {
        orderRepository.deleteOrderById(id);

    }

    @Override
    public void deleteAllOrders(List<Long> ids) {
        orderRepository.deleteAllOrders(ids);
    }
}
