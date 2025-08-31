package com.uade.tpo.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.demo.entity.Discount;
import com.uade.tpo.demo.repository.DiscountRepository;

@Service
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    private DiscountRepository discountRepository;

    @Override
    public Discount createDiscount(Discount discount) {
        if (discountRepository.existsById(discount.getId())) {
            throw new IllegalArgumentException("Discount with ID " + discount.getId() + " already exists.");
        }
        return discountRepository.save(discount);
    }

    @Override
    public List<Discount> getAllDiscounts() {
        return discountRepository.findAll();
    }

    @Override
    public Discount getDiscountById(Long id) {
        return discountRepository.findById(id).orElse(null);
    }

    @Override
    public Discount updateDiscount(Discount discount) {
        if (!discountRepository.existsById(discount.getId())) {
            throw new IllegalArgumentException("Discount with ID " + discount.getId() + " does not exist.");
        }
        return discountRepository.save(discount);
    }

    @Override
    public void deleteDiscount(Long id) {
        discountRepository.deleteById(id);
    }
    
}
