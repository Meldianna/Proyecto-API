package com.uade.tpo.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.demo.entity.PaymentMethod;
import com.uade.tpo.demo.entity.dto.PaymentMethodRequest;
import com.uade.tpo.demo.exceptions.NoSuchPaymentMethodException;
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
    public void deleteById(Long id) {

        if (!paymentMethodRepository.existsById(id)) {
            throw new NoSuchPaymentMethodException();
        }
        paymentMethodRepository.deleteById(id);
    }



    @Override
     public void updatePaymentMethod( Long id, PaymentMethodRequest request) throws NoSuchPaymentMethodException {
        PaymentMethod existingPaymentMethod = paymentMethodRepository.findById(id)
            .orElseThrow(NoSuchPaymentMethodException::new);

        existingPaymentMethod.setDescription(request.getDescription());
        paymentMethodRepository.save(existingPaymentMethod);
    }



}
