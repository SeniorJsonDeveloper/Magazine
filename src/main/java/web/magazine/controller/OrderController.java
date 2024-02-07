package web.magazine.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.magazine.dto.mapper.OrderMapper;
import web.magazine.dto.requests.UpsertOrderRequest;
import web.magazine.dto.responses.ListOrderResponse;
import web.magazine.dto.responses.OrderResponse;
import web.magazine.filter.OrderFilter;
import web.magazine.service.ClientService;
import web.magazine.service.OrderService;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/order")
public class OrderController {
    private final OrderMapper orderMapperImpl;
    private final OrderService dataBaseOrderServiceImpl;
    @GetMapping("/filter")
    public ResponseEntity<ListOrderResponse> showOrdersWithFilter(OrderFilter filter){
//
//        orderFilter.setMinPrice(orderFilter.getMinPrice());
//        orderFilter.setMaxPrice(orderFilter.getMaxPrice());
        return ResponseEntity.ok(
                orderMapperImpl.
                        responseListToList(
                                dataBaseOrderServiceImpl
                                        .withFilter(filter)
                        ));
    }
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> showOrderById(@PathVariable Long id){
        return ResponseEntity.ok(
                orderMapperImpl.response(
                        dataBaseOrderServiceImpl.findById(id)
                )
        );

    }
    @GetMapping
    public ResponseEntity<ListOrderResponse> showOrders(){
        return ResponseEntity.ok(orderMapperImpl
                .responseListToList(dataBaseOrderServiceImpl.getAllOrders()));
    }
    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody UpsertOrderRequest request){
        var order = dataBaseOrderServiceImpl
                .createOrder(
                        orderMapperImpl
                                .requestToOrder(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(
                orderMapperImpl.response(dataBaseOrderServiceImpl.createOrder(order)));
    }
    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable Long id,
                                                     @RequestBody UpsertOrderRequest request){
        var order = dataBaseOrderServiceImpl
                .updateOrder(orderMapperImpl
                        .requestToOrder(id,request));
        return ResponseEntity.ok(
                orderMapperImpl
                        .response(dataBaseOrderServiceImpl
                                .updateOrder(order))
        );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id){
        dataBaseOrderServiceImpl.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
