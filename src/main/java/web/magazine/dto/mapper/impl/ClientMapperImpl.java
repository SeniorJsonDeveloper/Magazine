package web.magazine.dto.mapper.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import web.magazine.dto.mapper.ClientMapper;
import web.magazine.dto.mapper.OrderMapper;
import web.magazine.dto.requests.UpsertClientRequest;
import web.magazine.dto.responses.ClientResponse;
import web.magazine.dto.responses.ClientResponseList;
import web.magazine.entities.Client;
import web.magazine.exception.BadRequestException;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ClientMapperImpl implements ClientMapper {
    private final OrderMapper orderMapperImpl;
    @Override
    public Client requestToClient(UpsertClientRequest request) {
        Client client = new Client();
        client.setUsername(request.getName());
        if (request.getName().isEmpty()){
            throw new BadRequestException("имя не может быть пустым!");
        }
        client.setPhoneNumber(request.getPhoneNumber());
        if (request.getPhoneNumber().isEmpty()){
            throw new BadRequestException("номер телефона не может быть пустым!");
        }
        return client;


    }

    @Override
    public Client requestToClient(Long id,UpsertClientRequest request) {
          Client client = requestToClient(request);
          client.setId(id);
          return client;
    }

    @Override
    public ClientResponse response(Client client) {
        ClientResponse clientResponse = new ClientResponse();
        clientResponse.setId(client.getId());
        clientResponse.setName(client.getUsername());
        clientResponse.setOrders(orderMapperImpl
                .responseList(
                        client.getOrders()));
        return clientResponse;
    }

    @Override
    public List<ClientResponse> responseToList(List<Client> clients) {
        return clients
                .stream()
                .map(this::response)
                .collect(Collectors.toList());
    }

    @Override
    public ClientResponseList responseListToList(List<Client> clients) {
        ClientResponseList clientResponseList = new ClientResponseList();
        clientResponseList.setClientResponses(
                clients.stream()
                        .map(this::response)
                        .collect(Collectors.toList())
        );
        return clientResponseList;
    }
}
