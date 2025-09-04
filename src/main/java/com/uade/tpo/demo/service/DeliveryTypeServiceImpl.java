package com.uade.tpo.demo.service;

import com.uade.tpo.demo.entity.DeliveryType;
import com.uade.tpo.demo.exceptions.DuplicateDeliveryException;
import com.uade.tpo.demo.repository.DeliveryTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryTypeServiceImpl implements DeliveryTypeService {

    @Autowired
    private final DeliveryTypeRepository deliveryTypeRepository;

    public DeliveryTypeServiceImpl(DeliveryTypeRepository deliveryTypeRepository) {
        this.deliveryTypeRepository = deliveryTypeRepository;
    }

    @Override
    public DeliveryType createDeliveryType(DeliveryType deliveryType) throws DuplicateDeliveryException {
        if (deliveryTypeRepository.existsByDescription(deliveryType.getDescription())) {
        throw new DuplicateDeliveryException(); // Lanza la excepcion
    }
    return deliveryTypeRepository.save(deliveryType);
    }

    @Override
    public DeliveryType updateDeliveryType(Long id, DeliveryType deliveryType) {
        DeliveryType existing = deliveryTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DeliveryType no encontrado"));
        existing.setDescription(deliveryType.getDescription());
        return deliveryTypeRepository.save(existing);
    }

    @Override
    public void deleteDeliveryType(Long id) {
        if (!deliveryTypeRepository.existsById(id)) {
            throw new RuntimeException("DeliveryType no encontrado");
        }
        deliveryTypeRepository.deleteById(id);
    }

    @Override
    public List<DeliveryType> getAllDeliveryTypes() {
        return deliveryTypeRepository.findAll();
    }

    @Override
    public DeliveryType getDeliveryTypeById(Long id) {
        return deliveryTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DeliveryType no encontrado"));
    }
}
