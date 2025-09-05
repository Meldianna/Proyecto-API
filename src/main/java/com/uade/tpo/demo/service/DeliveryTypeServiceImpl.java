package com.uade.tpo.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.demo.entity.DeliveryType;
import com.uade.tpo.demo.entity.dto.DeliveryTypeRequest;
import com.uade.tpo.demo.entity.dto.DeliveryTypeResponse;
import com.uade.tpo.demo.exceptions.DuplicateDeliveryException;
import com.uade.tpo.demo.repository.DeliveryTypeRepository;

@Service
public class DeliveryTypeServiceImpl implements DeliveryTypeService {

    @Autowired
    private DeliveryTypeRepository deliveryTypeRepository;

    @Override
    public DeliveryTypeResponse createDeliveryType(DeliveryTypeRequest deliveryTypeRequest) throws DuplicateDeliveryException {
        if (deliveryTypeRepository.existsByDescription(deliveryTypeRequest.getDescription())) {
        throw new DuplicateDeliveryException(); // Lanza la excepcion
        }
        DeliveryType newDeliveryType = new DeliveryType();
        newDeliveryType.setDescription(deliveryTypeRequest.getDescription()); //"mapeo" entidad con DTORequest
        DeliveryType savedDeliveryType = deliveryTypeRepository.save(newDeliveryType); //guardamos la entidad, pero ahora queda devolver el DTOResponse

        return new DeliveryTypeResponse(savedDeliveryType.getId(), savedDeliveryType.getDescription()); //retornamos y creamos el nuevo DTO con los datos de la entidad guardada
    }

    @Override
    public DeliveryTypeResponse updateDeliveryType(Long id, DeliveryTypeRequest deliveryTypeRequest) {
        DeliveryType existing = deliveryTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DeliveryType no encontrado"));
        existing.setDescription(deliveryTypeRequest.getDescription());
        DeliveryType updatedDeliveryType = deliveryTypeRepository.save(existing);

        return new DeliveryTypeResponse(updatedDeliveryType.getId(), updatedDeliveryType.getDescription());
    }

    @Override
    public void deleteDeliveryType(Long id) {
        if (!deliveryTypeRepository.existsById(id)) {
            throw new RuntimeException("DeliveryType no encontrado");
        }
        deliveryTypeRepository.deleteById(id);
    }

    @Override
    public List<DeliveryTypeResponse> getAllDeliveryTypes() {
        List<DeliveryType> listDeliveryTypes =  deliveryTypeRepository.findAll();
        List<DeliveryTypeResponse> newListDeliveryTypeResponse = listDeliveryTypes.stream()
                .map(dt -> new DeliveryTypeResponse(dt.getId(), dt.getDescription()))
                .collect(Collectors.toList());
        return newListDeliveryTypeResponse;
    }

    @Override
    public DeliveryTypeResponse getDeliveryTypeById(Long id) {
        DeliveryType deliveryType = deliveryTypeRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("DeliveryType no encontrado con id: " + id));

        return new DeliveryTypeResponse(deliveryType.getId(), deliveryType.getDescription());
    }
}
