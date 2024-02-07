package web.magazine.dto.requests;

import lombok.Data;

@Data
public class UpsertClientRequest {
    private String name;
    private String phoneNumber;

}
