package com.uade.tpo.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.uade.tpo.demo.repository.OrderRepository;

import com.uade.tpo.demo.entity.DeliveryType;
import com.uade.tpo.demo.entity.Status;
import com.uade.tpo.demo.entity.Order;
import com.uade.tpo.demo.entity.PaymentMethod;
import com.uade.tpo.demo.entity.User;

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


    
    public Order createOrder(float totalPrice, User user, LocalDateTime date, Status status, DeliveryType deliveryType, 
    PaymentMethod paymentMethod) throws OrderDuplicateException {
            Order order = new Order(totalPrice, date, user, status, deliveryType, paymentMethod);
            return orderRepository.save(order);
    }


    public ResponseEntity<Object> deleteById(@PathVariable Long orderId) {
        orderRepository.deleteById(orderId);
        return ResponseEntity.noContent().build();  
    }




    public String putMethodName(@PathVariable String id, @RequestBody String entity) {
        return orderRepository.findById(Long.valueOf(id)).map(order -> {
            // Aquí puedes actualizar los campos del objeto 'order' con los valores del 'entity'
            // Por ejemplo, si 'entity' es un JSON, puedes mapear sus campos al objeto 'order'
            // order.setCampo(entity.getCampo());
            // order.setOtroCampo(entity.getOtroCampo());
            // Guarda el objeto actualizado en la base de datos
            orderRepository.save(order);
            return "Order updated successfully";
        }).orElse("Order not found");
    }

    


    
}
