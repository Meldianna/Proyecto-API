package com.uade.tpo.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.uade.tpo.demo.entity.Order;
import com.uade.tpo.demo.entity.dto.OrderRequest;
import com.uade.tpo.demo.exceptions.OrderDuplicateException;
import com.uade.tpo.demo.repository.OrderRepository;

@Service
public class OrderServiceImp implements OrderService {


    @Autowired
    private OrderRepository orderRepository;


    @Override
    public List<Order> getOrders() {
        return  orderRepository.findAll();
    }




    @Override
    public Optional<Order> getOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }


    @Override
    public Order createOrder(OrderRequest orderRequest) throws OrderDuplicateException {
            Order order = new Order(orderRequest.getTotalPrice(),
            orderRequest.getDate(),
            orderRequest.getUserId(),
            orderRequest.getDeliveryType(),
            orderRequest.getPaymentMethod());
            return orderRepository.save(order);
    }

    @Override
    public ResponseEntity<Object> deleteById(@PathVariable Long orderId) {
        orderRepository.deleteById(orderId);
        return ResponseEntity.noContent().build();  
    }



    @Override
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
