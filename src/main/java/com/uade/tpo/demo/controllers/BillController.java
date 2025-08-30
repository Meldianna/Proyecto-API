package com.uade.tpo.demo.controllers;

import java.net.URI;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.demo.entity.Bill;
import com.uade.tpo.demo.entity.dto.BillRequest;
import com.uade.tpo.demo.entity.dto.BillResponse;
import com.uade.tpo.demo.exceptions.BillDuplicateException;
import com.uade.tpo.demo.exceptions.NoBillWithDateException;
import com.uade.tpo.demo.exceptions.NoBillWithOrderId;
import com.uade.tpo.demo.exceptions.NoBillWithUserId;
import com.uade.tpo.demo.exceptions.NoUserIdException;
import com.uade.tpo.demo.service.BillService;

@RestController
@RequestMapping("bills")
public class BillController {
    
    @Autowired
    private BillService billService;

    @GetMapping
    public ResponseEntity<Page<BillResponse>> getBills(
        @RequestParam(required = false) Integer page,
        @RequestParam(required = false) Integer size){
        return page == null || size == null
            ? ResponseEntity.ok(billService.getBills(PageRequest.of(0, Integer.MAX_VALUE)))
            : ResponseEntity.ok(billService.getBills(PageRequest.of(page, size)));
            
        }
    @GetMapping("/billId/{billId}")
    public ResponseEntity<BillResponse> getBillsById(@PathVariable Long billId){
        Optional<BillResponse> result = billService.getBillsById(billId);
        if(result.isPresent()){
            return ResponseEntity.ok(result.get());
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    
    @GetMapping("/user/{userId}")
    public ResponseEntity<Page<BillResponse>> getBillsByUserId(
        @PathVariable Long userId,
        @RequestParam(required = false) Integer page,
        @RequestParam(required = false) Integer size)
        throws NoBillWithUserId, NoUserIdException{
        Pageable pageable = (page == null || size == null)
            ? PageRequest.of(0, Integer.MAX_VALUE)
            : PageRequest.of(page, size);
        Page<BillResponse> billPage = billService.getBillsByUserId(userId, pageable);
        if(billPage.hasContent()){
            return ResponseEntity.ok(billPage);
        }else{
            return ResponseEntity.noContent().build();
        }
        
    }
    
    @GetMapping("/order/{orderId}")
    public ResponseEntity<BillResponse> getBillsByOrderId(@PathVariable Long orderId) throws NoBillWithOrderId{
        Optional<BillResponse> result = billService.getBillsByOrderId(orderId);
        if(result.isPresent()){
            return ResponseEntity.ok(result.get());
        }else{
            return ResponseEntity.noContent().build();
        }
        
    }
        
    @GetMapping("/date")
    public ResponseEntity<Page<BillResponse>> getBillsByDate( //date?date=yyyy-mm-dd
        @RequestParam LocalDate date, //sólo acepta formato fecha yyyy-mm-dd (universal)
        @RequestParam(required = false) Integer page,
        @RequestParam(required = false) Integer size)
        throws NoBillWithDateException{
        Pageable pageable = (page == null || size == null)
            ? PageRequest.of(0, Integer.MAX_VALUE)
            : PageRequest.of(page, size);
        Page<BillResponse> billPage = billService.getBillsByDate(date, pageable);
        if(billPage.hasContent()){
            return ResponseEntity.ok(billPage);
        }else{
            return ResponseEntity.noContent().build();
        }
        
    }
    
    @PostMapping
    public ResponseEntity<Object> createBill(@RequestBody BillRequest billRequest)
            throws BillDuplicateException{
                try {
                    Bill result = billService.createBill(billRequest.getIdOrder(),  billRequest.getPrecioTotal(), billRequest.getFecha());
                    return ResponseEntity.created(URI.create("bills/" + result.getId())).body(result);
                } catch (NoBillWithOrderId nbwoi) {
                    return ResponseEntity.notFound().build();
                }
       
        
    }


}
