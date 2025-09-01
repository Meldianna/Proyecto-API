package com.uade.tpo.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.demo.entity.PaymentMethod;
import com.uade.tpo.demo.entity.dto.PaymentMethodRequest;
import com.uade.tpo.demo.exceptions.PaymentMethodDuplicateException;
import com.uade.tpo.demo.repository.PaymentMethodRepository;

@Service
public class PaymentMethodServiceImp implements PaymentMethodService {

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Override
    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethodRepository.findAll();
    }


    @Override
    public PaymentMethod createPaymentMethod(PaymentMethodRequest paymentMethodRequest) throws PaymentMethodDuplicateException{
        PaymentMethod paymentMethod = new PaymentMethod(paymentMethodRequest.getDescription());
            return paymentMethodRepository.save(paymentMethod);
    }




    @Override
    public void deleteById(String id) {
        paymentMethodRepository.deleteById(id);
    }



}
