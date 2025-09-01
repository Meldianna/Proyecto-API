package com.uade.tpo.demo.service;


import java.util.List;
import java.util.Optional;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.uade.tpo.demo.entity.Order;
import com.uade.tpo.demo.entity.dto.OrderRequest;
import com.uade.tpo.demo.exceptions.OrderDuplicateException;

public interface OrderService {


    public List<Order> getOrders();




    public Optional<Order> getOrderById(Long orderId);

    
    public Order createOrder(OrderRequest orderRequest ) throws OrderDuplicateException;


    public void deleteById(@PathVariable Long orderId);



    public String putMethodName(@PathVariable String id, @RequestBody String entity);


}
