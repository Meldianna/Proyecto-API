package com.uade.tpo.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.demo.entity.PaymentMethod;
import com.uade.tpo.demo.entity.dto.PaymentMethodRequest;
import com.uade.tpo.demo.entity.dto.PaymentMethodResponse;
import com.uade.tpo.demo.exceptions.NoSuchPaymentMethodException;
import com.uade.tpo.demo.exceptions.PaymentMethodDuplicateException;
import com.uade.tpo.demo.repository.PaymentMethodRepository;

import jakarta.transaction.Transactional;

@Service
public class PaymentMethodServiceImp implements PaymentMethodService {

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Override
    public List<PaymentMethodResponse> getPaymentMethods() {
       List<PaymentMethod> listPaymentMethods =  paymentMethodRepository.findAll();
        List<PaymentMethodResponse> newListDTO = listPaymentMethods.stream()
                .map(dt -> new PaymentMethodResponse(dt.getId(), dt.getDescription()))
                .collect(Collectors.toList());
        return newListDTO;
    }


    @Override
    @Transactional
    public PaymentMethodResponse createPaymentMethod(PaymentMethodRequest paymentMethodRequest) throws PaymentMethodDuplicateException{
        PaymentMethod paymentMethod = new PaymentMethod(paymentMethodRequest.getDescription());
            return toPaymentResponse(paymentMethodRepository.save(paymentMethod));
    }




    @Override
    @Transactional
    public void deleteById(Long id) {

        if (!paymentMethodRepository.existsById(id)) {
            throw new NoSuchPaymentMethodException();
        }
        paymentMethodRepository.deleteById(id);
    }



    @Override
    @Transactional
     public void updatePaymentMethod( Long id, PaymentMethodRequest request) throws NoSuchPaymentMethodException {
        PaymentMethod existingPaymentMethod = paymentMethodRepository.findById(id)
            .orElseThrow(NoSuchPaymentMethodException::new);

        existingPaymentMethod.setDescription(request.getDescription());
        paymentMethodRepository.save(existingPaymentMethod);
    }

    public PaymentMethodResponse toPaymentResponse(PaymentMethod method){
        return new PaymentMethodResponse(method.getId(), method.getDescription());
    }


}
