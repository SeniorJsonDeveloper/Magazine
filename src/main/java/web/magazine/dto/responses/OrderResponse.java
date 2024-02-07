package web.magazine.dto.responses;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderResponse {
    private Long id;
    private String productName;
    private BigDecimal price;


}
