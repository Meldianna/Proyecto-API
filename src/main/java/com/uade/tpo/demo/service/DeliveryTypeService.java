package com.uade.tpo.demo.service;

import java.util.List;

import com.uade.tpo.demo.entity.dto.DeliveryTypeRequest;
import com.uade.tpo.demo.entity.dto.DeliveryTypeResponse;
import com.uade.tpo.demo.exceptions.DuplicateDeliveryException;

public interface DeliveryTypeService {
    DeliveryTypeResponse createDeliveryType(DeliveryTypeRequest deliveryTypeRequest) throws DuplicateDeliveryException;
    DeliveryTypeResponse updateDeliveryType(Long id, DeliveryTypeRequest deliveryTypeRequest);
    void deleteDeliveryType(Long id);
    List<DeliveryTypeResponse> getAllDeliveryTypes();
    DeliveryTypeResponse getDeliveryTypeById(Long id);
}
