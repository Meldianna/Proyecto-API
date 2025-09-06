package com.uade.tpo.demo.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.demo.entity.Order;
import com.uade.tpo.demo.entity.dto.OrderRequest;
import com.uade.tpo.demo.entity.dto.OrderResponse;
import com.uade.tpo.demo.exceptions.NoSuchDeliveryTypeException;
import com.uade.tpo.demo.exceptions.NoSuchPaymentMethodException;
import com.uade.tpo.demo.exceptions.NoUserIdException;
import com.uade.tpo.demo.exceptions.OrderDuplicateException;
import com.uade.tpo.demo.repository.DeliveryTypeRepository;
import com.uade.tpo.demo.repository.PaymentMethodRepository;
import com.uade.tpo.demo.service.OrderService;



@RestController
@RequestMapping("orders")
public class OrdersController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private DeliveryTypeRepository deliveryTypeRepository;

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;


    @GetMapping
    public ResponseEntity<List<Order>> getOrders() {
        List<Order> orders = orderService.getOrders();
        return ResponseEntity.ok(orders);
    }


    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderId) {
        Optional<Order> result = orderService.getOrderById(orderId);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());

        return ResponseEntity.noContent().build();
    }


    @PostMapping
    public ResponseEntity<Object> createOrder(@RequestBody OrderRequest orderRequest)
            throws OrderDuplicateException, 
            NoSuchDeliveryTypeException, 
            NoSuchPaymentMethodException, NoUserIdException {
        Order result = orderService.createOrder(orderRequest);
        return ResponseEntity.created(URI.create("/orders/" + result.getId())).body(result);
    }


    @PostMapping("/checkout/{cartId}")
    public ResponseEntity<OrderResponse> checkout(@RequestBody OrderRequest orderRequest, @PathVariable Long cartId) throws NoSuchDeliveryTypeException, 
                                            NoSuchPaymentMethodException, 
                                            OrderDuplicateException, NoUserIdException {
    //DeliveryType delivery = deliveryTypeRepository.findById(deliveryTypeId).orElseThrow();
    //PaymentMethod payment = paymentMethodRepository.findById(paymentMethodId).orElseThrow();
    OrderResponse order = orderService.checkout(orderRequest);
    return ResponseEntity.ok().body(order);
}
    
}
