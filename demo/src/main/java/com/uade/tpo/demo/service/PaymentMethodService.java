package com.uade.tpo.demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.uade.tpo.demo.entity.PaymentMethod;
import com.uade.tpo.demo.entity.dto.PaymentMethodRequest;
import com.uade.tpo.demo.exceptions.NoSuchPaymentMethodException;
import com.uade.tpo.demo.exceptions.PaymentMethodDuplicateException;

public interface PaymentMethodService {

    public List<PaymentMethod> getPaymentMethods();

    public PaymentMethod createPaymentMethod(PaymentMethodRequest paymentMethodRequest) throws PaymentMethodDuplicateException;


    public void deleteById(Long id) throws NoSuchPaymentMethodException;



    public void updatePaymentMethod(Long id, PaymentMethodRequest request) throws NoSuchPaymentMethodException;

    
} 
