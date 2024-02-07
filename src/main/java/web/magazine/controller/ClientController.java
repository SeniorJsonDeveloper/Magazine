package web.magazine.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.magazine.dto.mapper.ClientMapper;
import web.magazine.dto.requests.CreateClientWithOrders;
import web.magazine.dto.requests.UpsertClientRequest;
import web.magazine.dto.responses.ClientResponse;
import web.magazine.dto.responses.ClientResponseList;
import web.magazine.dto.responses.ListOrderResponse;
import web.magazine.entities.Client;
import web.magazine.entities.Order;
import web.magazine.filter.ClientFilter;
import web.magazine.service.ClientService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/client")
public class ClientController {
    private final ClientMapper clientMapper;
    private final ClientService dataBaseClientServiceImpl;
    @GetMapping("/filter")
    public ResponseEntity<ClientResponseList> fetchWithFilter(ClientFilter filter){
        return ResponseEntity.ok(clientMapper
                .responseListToList(dataBaseClientServiceImpl
                        .findWithFilter(filter)));
    }

    @GetMapping
    public ResponseEntity<ClientResponseList> fetchAllClients(){
        return ResponseEntity.ok(clientMapper
                .responseListToList(dataBaseClientServiceImpl
                        .getAllClients()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ClientResponse> findClientById(@PathVariable Long id){
        return ResponseEntity.ok(
                clientMapper
                        .response(dataBaseClientServiceImpl.findById(id))
        );
    }
    @PostMapping
    public ResponseEntity<ClientResponse> createClient(@RequestBody UpsertClientRequest request){
        var client = dataBaseClientServiceImpl.createClient(clientMapper.requestToClient(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(clientMapper.response(dataBaseClientServiceImpl.createClient(client)));
    }
    @PostMapping("/create-with-order")
    public ResponseEntity<ClientResponse> createClientWithOrder(@RequestBody
                                                                CreateClientWithOrders client){
        Client existedClient = Client.builder().username(client.getName()).build();
        List<Order> orders = client.getOrders()
                .stream()
                .map(upsertOrderRequest -> Order
                        .builder()
                        .product(upsertOrderRequest.getProductName())
                        .price(upsertOrderRequest.getPrice())
                        .build()).toList();
        return ResponseEntity.ok(clientMapper.response(dataBaseClientServiceImpl
                .createClientWithOrder(existedClient,orders)));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ClientResponse> updateClient(@PathVariable Long id,
                                                       @RequestBody UpsertClientRequest request){
        var requireUser = dataBaseClientServiceImpl
                .updateClient(clientMapper
                        .requestToClient(id,request));
        return ResponseEntity.ok(
                clientMapper
                        .response(dataBaseClientServiceImpl
                                .updateClient(requireUser)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id){
        dataBaseClientServiceImpl.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
