package web.magazine.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import web.magazine.entities.Client;
import web.magazine.entities.Order;
import web.magazine.repository.ClientRepository;
import web.magazine.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
@RequiredArgsConstructor
public class InMemoryOrderRepository implements OrderRepository {
    private ClientRepository clientRepository;
    private final Map<Long,Order> repository = new ConcurrentHashMap<>();
    private final AtomicLong currentId = new AtomicLong(1);
    @Autowired
    @Lazy
    public void setClientRepositoryImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Order> fetchAll() {
        return new ArrayList<>(repository.values());
    }

    @Override
    public Optional<Order> findById(Long id) {
        return Optional.ofNullable(repository.get(id));
    }

    @Override
    public Order createOrder(Order order) {
        Long orderId = currentId.getAndIncrement();
        Long clientId = order.getClient().getId();
        Client client = clientRepository
                .findById(clientId)
                .orElseThrow(()->new RuntimeException(
                        String.format("Client with \"%s\" id not found",clientId)
                ));
        order.setId(orderId);
        order.setClient(client);
        repository.put(orderId,order);
        client.addOrder(order);
        return order;
    }

    @Override
    public Order updateOrder(Order order) {
        Long orderId = order.getId();
        Order updatingOrder = repository.get(orderId);
        updatingOrder.setId(orderId);
        repository.put(orderId,order);
        return order;

    }

    @Override
    public void deleteOrderById(Long id) {
        repository.remove(id);

    }

    @Override
    public void deleteAllOrders(List<Long> ids) {
        ids.forEach(repository::remove);

    }
}
