package com.uade.tpo.demo.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.uade.tpo.demo.entity.Bill;
import com.uade.tpo.demo.entity.Order;
import com.uade.tpo.demo.entity.User;
import com.uade.tpo.demo.exceptions.BillDuplicateException;
import com.uade.tpo.demo.exceptions.NoBillWithDateException;
import com.uade.tpo.demo.exceptions.NoBillWithOrderId;
import com.uade.tpo.demo.exceptions.NoBillWithUserId;


public interface BillService {
    public Page<Bill> getBills(PageRequest pageRequest);
    public Optional<Bill> getBillsById(Long facturaId);
    public Page<Bill> getBillsByUserId(User userId, Pageable pageable) throws NoBillWithUserId;
    public Optional<Bill> getBillsByOrderId(Order orderId) throws NoBillWithOrderId;
    public Page<Bill> getBillsByDate(LocalDate date, Pageable pageable) throws NoBillWithDateException;
    public Bill createBill(Order orderId, User userId, Double precioTotal, LocalDate fecha) throws BillDuplicateException;
}
