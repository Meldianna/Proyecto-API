package com.uade.tpo.demo.service;

import java.util.List;

import com.uade.tpo.demo.entity.dto.PaymentMethodRequest;
import com.uade.tpo.demo.entity.dto.PaymentMethodResponse;
import com.uade.tpo.demo.exceptions.NoSuchPaymentMethodException;
import com.uade.tpo.demo.exceptions.PaymentMethodDuplicateException;

public interface PaymentMethodService {

    public List<PaymentMethodResponse> getPaymentMethods();

    public PaymentMethodResponse createPaymentMethod(PaymentMethodRequest paymentMethodRequest) throws PaymentMethodDuplicateException;


    public void deleteById(Long id) throws NoSuchPaymentMethodException;
    public void updatePaymentMethod(Long id, PaymentMethodRequest request) throws NoSuchPaymentMethodException;

    
} 
