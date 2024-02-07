package web.magazine.filter;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor @FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderFilter {
    BigDecimal minPrice;
    BigDecimal maxPrice;
    Instant beforeCreate;
    Instant beforeUpdate;
    String product;
    Integer pageSize;
    Integer pageNumber;
    String category;
}
