package web.magazine.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import web.magazine.dto.requests.UpsertOrderRequest;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateClientWithOrders {
    private String name;
    private List<UpsertOrderRequest> orders = new ArrayList<>();
}
