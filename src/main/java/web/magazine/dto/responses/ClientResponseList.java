package web.magazine.dto.responses;

import lombok.Data;
import web.magazine.dto.responses.ClientResponse;

import java.util.ArrayList;
import java.util.List;

@Data
public class ClientResponseList {
    private List<ClientResponse> clientResponses = new ArrayList<>();

}
