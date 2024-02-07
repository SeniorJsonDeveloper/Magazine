package web.magazine.filter;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClientFilter {
    private String username;
    private Integer pageNumber;
    private Integer pageSize;
}
