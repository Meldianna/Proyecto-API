package com.uade.tpo.demo.service;

import java.util.List;
import com.uade.tpo.demo.entity.DeliveryType;
import com.uade.tpo.demo.exceptions.DuplicateDeliveryException;

public interface DeliveryTypeService {
    DeliveryType createDeliveryType(DeliveryType deliveryType) throws DuplicateDeliveryException;
    DeliveryType updateDeliveryType(Long id, DeliveryType deliveryType);
    void deleteDeliveryType(Long id);
    List<DeliveryType> getAllDeliveryTypes();
    DeliveryType getDeliveryTypeById(Long id);
}
