package web.magazine.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import web.magazine.dto.responses.ListOrderResponse;
import web.magazine.dto.responses.OrderResponse;
import web.magazine.dto.requests.UpsertOrderRequest;
import web.magazine.entities.Order;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {
    Order requestToOrder(UpsertOrderRequest request);
    Order requestToOrder(Long id,UpsertOrderRequest request);
    OrderResponse response(Order order);
    List<OrderResponse> responseList(List<Order> orders);
    ListOrderResponse responseListToList(List<Order> orders);
}
