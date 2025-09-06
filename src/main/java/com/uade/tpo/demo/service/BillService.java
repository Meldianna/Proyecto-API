package com.uade.tpo.demo.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.uade.tpo.demo.entity.Bill;
import com.uade.tpo.demo.entity.dto.BillResponse;
import com.uade.tpo.demo.exceptions.BillDuplicateException;
import com.uade.tpo.demo.exceptions.NoBillWithDateException;
import com.uade.tpo.demo.exceptions.NoBillWithOrderId;
import com.uade.tpo.demo.exceptions.NoBillWithUserId;
import com.uade.tpo.demo.exceptions.NoUserIdException;


public interface BillService {
    public Page<BillResponse> getBills(PageRequest pageRequest);
    public Optional<BillResponse> getBillsById(Long id);
    public Page<BillResponse> getBillsByUserId(Long userId, Pageable pageable) throws NoBillWithUserId, NoUserIdException;
    public Optional<BillResponse> getBillsByOrderId(Long orderId) throws NoBillWithOrderId;
    public Page<BillResponse> getBillsByDate(LocalDate date, Pageable pageable) throws NoBillWithDateException;
    public Bill createBill(Long orderId, Double precioTotal, LocalDate fecha) throws BillDuplicateException, NoBillWithOrderId;
}
