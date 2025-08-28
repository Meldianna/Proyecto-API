package com.uade.tpo.demo.service;

import java.util.List;

import com.uade.tpo.demo.entity.Discount;

public interface DiscountService {

    Discount createDiscount(Discount discount);
    List<Discount> getAllDiscounts();
    Discount getDiscountById(Long id);
    Discount updateDiscount(Discount discount);
    void deleteDiscount(Long id);
}
