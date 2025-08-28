package com.uade.tpo.demo.service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.uade.tpo.demo.entity.DeliveryType;
import com.uade.tpo.demo.entity.Order;
import com.uade.tpo.demo.entity.Status;
import com.uade.tpo.demo.entity.PaymentMethod;
import com.uade.tpo.demo.entity.User;
import com.uade.tpo.demo.exceptions.OrderDuplicateException;

public interface OrderService {


    public List<Order> getOrders();




    public Optional<Order> getOrderById(Long orderId);

    
    
    public Order createOrder(float totalPrice, User userId, LocalDateTime date, Status status, DeliveryType deliveryType, 
    PaymentMethod paymentMethod) throws OrderDuplicateException;


    public ResponseEntity<Object> deleteById(@PathVariable Long orderId);



    public String putMethodName(@PathVariable String id, @RequestBody String entity);

}
