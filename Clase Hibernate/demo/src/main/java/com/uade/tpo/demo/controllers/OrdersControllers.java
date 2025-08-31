package com.uade.tpo.demo.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.demo.entity.Order;
import com.uade.tpo.demo.entity.dto.OrderRequest;
import com.uade.tpo.demo.exceptions.OrderDuplicateException;
import com.uade.tpo.demo.service.OrderService;

import lombok.Builder;



@Builder
@RestController
@RequestMapping("orders")
public class OrdersControllers {

    @Autowired
    private OrderService orderService;


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
            throws OrderDuplicateException {
        Order result = orderService.createOrder(orderRequest);
        return ResponseEntity.created(URI.create("/orders/" + result.getId())).body(result);
    }


    @DeleteMapping("delete/{orderId}")
    public ResponseEntity<Object> deleteById(@PathVariable Long orderId) {
        orderService.deleteById(orderId);
        return ResponseEntity.noContent().build();  
    }


    
    @PutMapping("path/{id}")
    public String putMethodName(@PathVariable String id, @RequestBody String entity) {
        return entity;
    }

    
}
