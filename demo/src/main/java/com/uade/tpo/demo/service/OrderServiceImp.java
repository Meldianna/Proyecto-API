package com.uade.tpo.demo.service;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.uade.tpo.demo.repository.OrderRepository;
import com.uade.tpo.demo.entity.Order;
import com.uade.tpo.demo.entity.dto.OrderRequest;
import com.uade.tpo.demo.exceptions.OrderDuplicateException;

@Service
public class OrderServiceImp implements OrderService {


    @Autowired
    private OrderRepository orderRepository;





    public List<Order> getOrders() {
        return  orderRepository.findAll();
    }





    public Optional<Order> getOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }


    /* 
    public Order createOrder(float totalPrice, User user, LocalDateTime date,  DeliveryType deliveryType, 
    PaymentMethod paymentMethod) throws OrderDuplicateException {
            Order order = new Order(totalPrice, date, user, deliveryType, paymentMethod);
            return orderRepository.save(order);
    }
    */







    @Override
    public Order createOrder(OrderRequest orderRequest) throws OrderDuplicateException {
        Order order = new Order(orderRequest.getTotalPrice(), orderRequest.getDate(),orderRequest.getUserId(),
         orderRequest.getDeliveryType(), orderRequest.getPaymentMethod());
            return orderRepository.save(order);
    }

    


    
}
