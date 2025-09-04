package com.uade.tpo.demo.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.demo.entity.DeliveryType;
import com.uade.tpo.demo.exceptions.DuplicateDeliveryException;
import com.uade.tpo.demo.service.DeliveryTypeService;

@RestController
@RequestMapping("/deliveryType")
public class DeliveryTypeController {
    
    private final DeliveryTypeService deliveryTypeService;

    public DeliveryTypeController(DeliveryTypeService deliveryTypeService) {
        this.deliveryTypeService = deliveryTypeService;
    }

    @PostMapping
    public ResponseEntity<DeliveryType> createDeliveryType(@RequestBody DeliveryType deliveryType) throws DuplicateDeliveryException {
        return ResponseEntity.ok(deliveryTypeService.createDeliveryType(deliveryType));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeliveryType> updateDeliveryType(@PathVariable Long id, @RequestBody DeliveryType deliveryType) {
        return ResponseEntity.ok(deliveryTypeService.updateDeliveryType(id, deliveryType));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeliveryType(@PathVariable Long id) {
        deliveryTypeService.deleteDeliveryType(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<DeliveryType>> getAllDeliveryTypes() {
        return ResponseEntity.ok(deliveryTypeService.getAllDeliveryTypes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryType> getDeliveryTypeById(@PathVariable Long id) {
        return ResponseEntity.ok(deliveryTypeService.getDeliveryTypeById(id));
    }

}
