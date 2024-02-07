package web.magazine.dto.mapper.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import web.magazine.dto.mapper.OrderMapper;
import web.magazine.dto.requests.UpsertOrderRequest;
import web.magazine.dto.responses.ListOrderResponse;
import web.magazine.dto.responses.OrderResponse;
import web.magazine.entities.Order;
import web.magazine.service.ClientService;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderMapperImpl implements OrderMapper {
    private final ClientService dataBaseClientServiceImpl;
    @Override
    public Order requestToOrder(UpsertOrderRequest request) {
        Order order = new Order();
        order.setProduct(request.getProductName());
        order.setPrice(request.getPrice());
        order.setCategory(request.getCategory());
        order.setClient(dataBaseClientServiceImpl.findById(request.getClientId()));
        return order;
    }

    @Override
    public Order requestToOrder(Long id, UpsertOrderRequest request) {
        Order order = requestToOrder(request);
        order.setId(id);
        return order;
    }

    @Override
    public OrderResponse response(Order order) {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(order.getId());
        orderResponse.setProductName(order.getProduct());
        orderResponse.setPrice(order.getPrice());
        return orderResponse;
    }

    @Override
    public List<OrderResponse> responseList(List<Order> orders) {
        return orders.stream()
                .map(this::response)
                .collect(Collectors.toList());
    }

    @Override
    public ListOrderResponse responseListToList(List<Order> orders) {
        ListOrderResponse listOrderResponse = new ListOrderResponse();
        listOrderResponse.setOrders(
                orders.stream()
                        .map(this::response)
                        .collect(Collectors.toList())
        );
        return listOrderResponse;
    }

}
