package com.uade.tpo.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uade.tpo.demo.entity.Bill;
import com.uade.tpo.demo.entity.Order;
import com.uade.tpo.demo.entity.User;
import com.uade.tpo.demo.exceptions.BillDuplicateException;
import com.uade.tpo.demo.exceptions.NoBillWithDateException;
import com.uade.tpo.demo.exceptions.NoBillWithOrderId;
import com.uade.tpo.demo.exceptions.NoBillWithUserId;
import com.uade.tpo.demo.repository.BillRepository;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillRepository billRepository;
	@Override
	public Page<Bill> getBills(PageRequest pageable) {
		return billRepository.findAll(pageable); //este metodo permite devolver una lista de facturas
    }
	@Override
	public Optional<Bill> getBillsById(Long billId){
		return billRepository.findById(billId);
	}
	@Override
	public Page<Bill> getBillsByUserId(User userId, Pageable pageable) throws NoBillWithUserId {
		Page<Bill> existingBills = billRepository.findByUserId(userId, pageable);
		if (existingBills.isEmpty()){
			throw new NoBillWithUserId();
		}
		return existingBills;
	}

	@Override
	public Optional<Bill> getBillsByOrderId(Order orderId) throws NoBillWithOrderId {
		Optional<Bill> existingBill = billRepository.findByOrderId(orderId);
		if (!existingBill.isPresent()){
			throw new NoBillWithOrderId();
		}
		return existingBill;
	}
	@Override
	public Page<Bill> getBillsByDate(LocalDate date, Pageable pageable) throws NoBillWithDateException {
		Page<Bill> existingBills = billRepository.findByDate(date, pageable);
		if (!existingBills.hasContent()){
			throw new NoBillWithDateException();
		}
		return billRepository.findByDate(date, pageable);
	}


	@Override
	public Bill createBill(Order orderId, Double precioTotal, LocalDate fecha) throws BillDuplicateException {
		Optional<Bill> existingBill = billRepository.findByOrderId(orderId);
		if (existingBill.isPresent()){
			throw new BillDuplicateException();
		}else{
			return billRepository.save(new Bill(orderId, precioTotal, fecha));
		}
	}
}
