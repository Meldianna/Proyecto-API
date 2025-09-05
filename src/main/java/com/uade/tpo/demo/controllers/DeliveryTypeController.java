package com.uade.tpo.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.demo.entity.dto.DeliveryTypeRequest;
import com.uade.tpo.demo.entity.dto.DeliveryTypeResponse;
import com.uade.tpo.demo.exceptions.DuplicateDeliveryException;
import com.uade.tpo.demo.service.DeliveryTypeService;

@RestController
@RequestMapping("/deliveryType")
public class DeliveryTypeController {
    
    @Autowired
    private DeliveryTypeService deliveryTypeService;

    @PostMapping
    public ResponseEntity<Object> createDeliveryType(@RequestBody DeliveryTypeRequest deliveryType) {
        try {
            return ResponseEntity.ok(deliveryTypeService.createDeliveryType(deliveryType));
        } catch (DuplicateDeliveryException e) {
           return ResponseEntity.badRequest().body("delivery repetido");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeliveryTypeResponse> updateDeliveryType(@PathVariable Long id, @RequestBody DeliveryTypeRequest deliveryType) {
        return ResponseEntity.ok(deliveryTypeService.updateDeliveryType(id, deliveryType));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeliveryType(@PathVariable Long id) {
        deliveryTypeService.deleteDeliveryType(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<DeliveryTypeResponse>> getAllDeliveryTypes() {
        return ResponseEntity.ok(deliveryTypeService.getAllDeliveryTypes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryTypeResponse> getDeliveryTypeById(@PathVariable Long id) {
        return ResponseEntity.ok(deliveryTypeService.getDeliveryTypeById(id));
    }

}
