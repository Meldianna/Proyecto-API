package com.uade.tpo.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.demo.entity.PaymentMethod;
import com.uade.tpo.demo.entity.dto.PaymentMethodRequest;
import com.uade.tpo.demo.exceptions.PaymentMethodDuplicateException;
import com.uade.tpo.demo.service.PaymentMethodService;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("payment-methods")
public class PaymentMethodController {


    @Autowired
    private PaymentMethodService paymentMethodService;

    @GetMapping
    public ResponseEntity<List<PaymentMethod>> getPaymentMethods() {
        List<PaymentMethod> paymentMethods = paymentMethodService.getPaymentMethods();
        return ResponseEntity.ok(paymentMethods);
    }

   
    

    @PostMapping
    public ResponseEntity<Object> createPaymentMethod(@RequestBody PaymentMethodRequest paymentMethodRequest)
        throws PaymentMethodDuplicateException {
    PaymentMethod result = paymentMethodService.createPaymentMethod(paymentMethodRequest);
    return ResponseEntity.created(URI.create("/payment-methods/" + result.getDescription())).body(result);
    }


    @DeleteMapping("delete/{id}")
    public void deleteById(@PathVariable String id) {
        paymentMethodService.deleteById(id);
    }



   

    


    
}
