package web.magazine.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import web.magazine.entities.Client;
import web.magazine.entities.Order;
import web.magazine.exception.BadRequestException;
import web.magazine.repository.ClientRepository;
import web.magazine.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class InMemoryClientRepository implements ClientRepository {
    private final Map<Long,Client> repository = new ConcurrentHashMap<>();
    private final AtomicLong currentId = new AtomicLong(1);
    private OrderRepository orderRepository;
    @Autowired
    @Lazy
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Client> fetchAll() {
        return new ArrayList<>(repository.values());
    }

    @Override
    public Optional<Client> findById(Long id) {
        return Optional.ofNullable(repository.get(id));
    }

    @Override
    public Client createClient(Client client) {
        Long clientId = currentId.getAndIncrement();
        client.setId(clientId);
        client.setUsername(client.getUsername());
        if (client.getUsername()==null){
            throw new BadRequestException("Имя не может быть пустым!");
        }
        repository.put(clientId,client);
        return client;
    }

    @Override
    public Client updateClient(Client client) {
        Long clientId = client.getId();
        Client existedClient = repository.get(clientId);
        client.setId(clientId);
        repository.put(clientId,existedClient);
        return existedClient;
    }

    @Override
    public void deleteClient(Long id) {
        Client client = repository.get(id);
        orderRepository
                .deleteAllOrders(
                        client
                                .getOrders()
                                .stream()
                                .map(Order::getId)
                                .collect(Collectors.toList()));

    }
}
