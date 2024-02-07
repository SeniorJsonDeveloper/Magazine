package web.magazine.dto.requests;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpsertOrderRequest {
    private String productName;
    private BigDecimal price;
    private String category;
    private Long clientId;
}
