package com.uade.tpo.demo.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.demo.entity.Cart;
import com.uade.tpo.demo.entity.CartItem;
import com.uade.tpo.demo.entity.DeliveryType;
import com.uade.tpo.demo.entity.Order;
import com.uade.tpo.demo.entity.OrderItems;
import com.uade.tpo.demo.entity.PaymentMethod;
import com.uade.tpo.demo.entity.User;
import com.uade.tpo.demo.entity.dto.OrderItemsResponse;
import com.uade.tpo.demo.entity.dto.OrderRequest;
import com.uade.tpo.demo.entity.dto.OrderResponse;
import com.uade.tpo.demo.exceptions.NoSuchDeliveryTypeException;
import com.uade.tpo.demo.exceptions.NoSuchPaymentMethodException;
import com.uade.tpo.demo.exceptions.NoUserIdException;
import com.uade.tpo.demo.exceptions.OrderDuplicateException;
import com.uade.tpo.demo.repository.CartRepository;
import com.uade.tpo.demo.repository.DeliveryTypeRepository;
import com.uade.tpo.demo.repository.OrderItemsRepository;
import com.uade.tpo.demo.repository.OrderRepository;
import com.uade.tpo.demo.repository.PaymentMethodRepository;
import com.uade.tpo.demo.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImp implements OrderService {


    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private DeliveryTypeRepository deliveryRepository;

    @Autowired
    private PaymentMethodRepository paymentRepository;

    @Autowired
    private OrderItemsRepository orderItemsRepository;


    //page
    //cambiar a Order DTO
    @Override
    public List<Order> getOrders() {
        return  orderRepository.findAll();
    }
    
    @Override
    public Optional<Order> getOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }


    @Override
    @Transactional
    public Order createOrder(OrderRequest orderRequest) throws OrderDuplicateException, NoUserIdException,
     NoSuchDeliveryTypeException,  NoSuchPaymentMethodException{

        //no considero necesaria una validación para ver si el carrito exsite, porque  el carrito debe existir para este punto
                 
        DeliveryType delivery = deliveryRepository.findById(orderRequest.getDeliveryType())
                .orElseThrow(() -> new NoSuchDeliveryTypeException());
        
        PaymentMethod payment = paymentRepository.findById(orderRequest.getPaymentMethod())
                .orElseThrow(() -> new NoSuchPaymentMethodException());
        
        Cart cart = cartRepository.findById(orderRequest.getCartId())
            .orElseThrow(() -> new RuntimeException("Cart no encontrado"));
        
        User user = userRepository.findById(orderRequest.getUserId())
                .orElseThrow(() -> new NoUserIdException());
        


        Order order = new Order(cart, user, delivery, payment);
            order.setDate(LocalDateTime.now());
            return orderRepository.save(order);
    }


    

    
    @Override
    @Transactional
    public OrderResponse checkout(OrderRequest orderRequest) throws OrderDuplicateException, NoUserIdException,
     NoSuchDeliveryTypeException,  NoSuchPaymentMethodException{
    

        // Crear orden vacía
        Order order = createOrder(orderRequest);

        // // Pasar CartItems a OrderItems 
        Cart cart = order.getCart(); //refrescamos el cart
        List<CartItem> cartItemList = cart.getCartItems();
        
        List<OrderItems> orderItems = CartItemsToOrderItems(cartItemList, order);
        //persistir los orderItems


        order.setItems(orderItems);
        saveOrderWithItems(order);

    
        // Calcular total de la orden
        //order.setItems(order)
        calculateTotalPrice(order);

        // Guardar la orden 
        Order savedOrder = orderRepository.save(order);

        // Vaciar carrito
        cart.getCartItems().clear();
        cartRepository.save(cart);

        return toOrderResponse(savedOrder);
    }


    
    public OrderResponse toOrderResponse(Order order){
        return new OrderResponse(
            order.getId(),
            order.getTotalPrice(),
            order.getDate(),
            order.getUser(),
            order.getDeliveryType(),
            order.getPaymentMethod(),
            toOrderItemResponse(order.getItems())
    );
    }
    

    

     private List<OrderItems> CartItemsToOrderItems(List<CartItem> cartItems, Order order) {
        List<OrderItems> orderItems = new ArrayList<>();

        for (CartItem cartItem : cartItems) {
            OrderItems orderItem = new OrderItems();
            orderItem.setOrder(order); 
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setUnitPrice(cartItem.getProduct().getPrice());
            orderItem.setTotalPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());

            orderItems.add(orderItem);
        }

        return orderItems;
    }
    

    
    public List<OrderItemsResponse> toOrderItemResponse(List<OrderItems> items){
        List<OrderItemsResponse> listOrderItems = new ArrayList<>();  

        for (OrderItems orderItem : items) {
            OrderItemsResponse dto = new OrderItemsResponse();
            //orderItem.setOrder(order);
            dto.setProductId(orderItem.getProduct().getId());
            dto.setQuantity(orderItem.getQuantity());
            dto.setUnitPrice(orderItem.getUnitPrice());
            dto.setTotalPrice(orderItem.getUnitPrice()*orderItem.getQuantity());
            
            listOrderItems.add(dto);
        }
        
        return listOrderItems;
        
    }
        

        @Transactional
        public void calculateTotalPrice(Order order) {
            double total = 0;
            for (OrderItems item : order.getItems()) {
                total += item.getTotalPrice();
            }
            order.setTotalPrice(total);
        }

        @Transactional
        public void saveOrderWithItems(Order order){
            //persistir los items
            for (OrderItems item : order.getItems()){
                orderItemsRepository.save(item);
            }
    
            //guardar la orden con los items guardados
            orderRepository.save(order);
        }

}




    

    


    

