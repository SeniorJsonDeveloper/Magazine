package web.magazine.dto.responses;

import lombok.Data;
import web.magazine.dto.responses.OrderResponse;

import java.util.ArrayList;
import java.util.List;

@Data
public class ListOrderResponse {
    private List<OrderResponse> orders = new ArrayList<>();
}
