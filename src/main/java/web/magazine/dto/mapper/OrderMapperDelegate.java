package web.magazine.dto.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import web.magazine.dto.requests.UpsertOrderRequest;
import web.magazine.entities.Order;
import web.magazine.service.ClientService;

@Component
@RequiredArgsConstructor
public abstract class OrderMapperDelegate implements OrderMapper{
    private final ClientService dataBaseClientServiceImpl;
    @Override
    public Order requestToOrder(UpsertOrderRequest request) {
        Order order = new Order();
        order.setProduct(request.getProductName());
        order.setPrice(request.getPrice());
        order.setCategory(request.getCategory());
        order.setClient(
                dataBaseClientServiceImpl.findById(order.getClient().getId())
        );
        return order;
    }

    @Override
    public Order requestToOrder(Long id, UpsertOrderRequest request) {
        Order order = requestToOrder(request);
        order.setId(id);
        return order;
    }
}
