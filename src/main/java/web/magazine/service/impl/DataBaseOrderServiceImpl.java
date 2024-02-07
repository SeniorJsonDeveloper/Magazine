package web.magazine.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.magazine.entities.Client;
import web.magazine.entities.Order;
import web.magazine.exception.NotFoundException;
import web.magazine.filter.OrderFilter;
import web.magazine.repository.DataBaseOrderRepository;
import web.magazine.repository.OrderSpecification;
import web.magazine.service.ClientService;
import web.magazine.service.OrderService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DataBaseOrderServiceImpl implements OrderService {
    private final DataBaseOrderRepository dataBaseOrderRepository;
    private final ClientService dataBaseClientServiceImpl;

    @Override
    public List<Order> withFilter(OrderFilter filter) {
        return dataBaseOrderRepository.findAll(OrderSpecification.withFilter(filter),
                PageRequest.of(
                        filter.getPageNumber(), filter.getPageSize()
                )).getContent();
    }

    @Override
    public List<Order> getAllOrders() {
        return dataBaseOrderRepository.findAll();
    }

    @Override
    public Order findById(Long id) {
        return dataBaseOrderRepository.findById(id)
                .orElseThrow(()->new RuntimeException(
                        String.format("Order with \"%s\" not found!",id)
                ));
    }

    @Override
    @Transactional
    public Order createOrder(Order order) {
        Client client = dataBaseClientServiceImpl.findById(order.getClient().getId());
        order.setClient(client);
        return dataBaseOrderRepository.save(order);
    }

    @Override
    @Transactional
    public Order updateOrder(Order order) {
        Client client = dataBaseClientServiceImpl.findById(order.getClient().getId());
        Order existedOrder = findById(order.getId());
        existedOrder.setClient(client);
        return dataBaseOrderRepository.saveAndFlush(existedOrder);
    }

    @Override
    public void deleteById(Long id) {
        if (id==null) {
            throw new NotFoundException(
                    String.format("Order with \"%s\" not found",id)
            );
        }
            dataBaseOrderRepository.deleteById(id);

    }

    @Override
    public void deleteAllOrders(List<Long> ids) {
        dataBaseOrderRepository.deleteAllById(ids);
    }
}
