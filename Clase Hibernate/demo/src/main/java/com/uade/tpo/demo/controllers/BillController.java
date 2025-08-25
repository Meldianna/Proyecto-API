package com.uade.tpo.demo.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.net.URI;
import java.time.LocalDate;
import java.util.Optional;

import com.uade.tpo.demo.exceptions.BillDuplicateException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.demo.entity.Bill;
import com.uade.tpo.demo.entity.Order;
import com.uade.tpo.demo.entity.User;
import com.uade.tpo.demo.entity.dto.BillRequest;
import com.uade.tpo.demo.exceptions.NoBillWithDateException;
import com.uade.tpo.demo.exceptions.NoBillWithOrderId;
import com.uade.tpo.demo.exceptions.NoBillWithUserId;
import com.uade.tpo.demo.service.BillService;

import io.micrometer.core.ipc.http.HttpSender;

@RestController
@RequestMapping("bills")
public class BillController {
    
    @Autowired
    private BillService billService;

    @GetMapping
    public ResponseEntity<Page<Bill>> getBills(
        @RequestParam(required = false) Integer page,
        @RequestParam(required = false) Integer size){
        return page == null || size == null
            ? ResponseEntity.ok(billService.getBills(PageRequest.of(0, Integer.MAX_VALUE)))
            : ResponseEntity.ok(billService.getBills(PageRequest.of(page, size)));
            
        }
    @GetMapping("/{billId}")
    public ResponseEntity<Bill> getBillsById(@PathVariable Long facturaId){
        Optional<Bill> result = billService.getBillsById(facturaId);
        if(result.isPresent()){
            return ResponseEntity.ok(result.get());
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{billsByUserId}")
    public ResponseEntity<Page<Bill>> getBillsByUserId(
        @PathVariable User userId,
        @RequestParam(required = false) Integer page,
        @RequestParam(required = false) Integer size)
        throws NoBillWithUserId{
        Pageable pageable = (page == null || size == null)
            ? PageRequest.of(0, Integer.MAX_VALUE)
            : PageRequest.of(page, size);
        Page<Bill> billPage = billService.getBillsByUserId(userId, pageable);
        if(billPage.hasContent()){
            return ResponseEntity.ok(billPage);
        }else{
            return ResponseEntity.noContent().build();
        }
        
    }

    @GetMapping("/{billsByOrderId}")
    public ResponseEntity<Bill> getBillsByOrderId(@PathVariable Order orderId) throws NoBillWithOrderId{
        Optional<Bill> result = billService.getBillsByOrderId(orderId);
        if(result.isPresent()){
            return ResponseEntity.ok(result.get());
        }else{
            return ResponseEntity.noContent().build();
        }
        
    }
        
    @GetMapping("/{billsByDate}")
    public ResponseEntity<Page<Bill>> getBillsByDate(
        @RequestParam LocalDate date, //sólo acepta formato fecha yyyy/mm/dd (universal)
        @RequestParam(required = false) Integer page,
        @RequestParam(required = false) Integer size)
        throws NoBillWithDateException{
        Pageable pageable = (page == null || size == null)
            ? PageRequest.of(0, Integer.MAX_VALUE)
            : PageRequest.of(page, size);
        Page<Bill> billPage = billService.getBillsByDate(date, pageable);
        if(billPage.hasContent()){
            return ResponseEntity.ok(billPage);
        }else{
            return ResponseEntity.noContent().build();
        }
        
    }
    
    @PostMapping
    public ResponseEntity<Object> createBill(@RequestBody BillRequest billRequest)
            throws BillDuplicateException{
        Bill result = billService.createBill(billRequest.getIdOrden(),  billRequest.getPrecioTotal(), billRequest.getFecha());
        return ResponseEntity.created(URI.create("bills/" + result.getId())).body(result);
        
    }


}
