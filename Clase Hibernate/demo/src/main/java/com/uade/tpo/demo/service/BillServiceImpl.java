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
import com.uade.tpo.demo.exceptions.NoUserIdException;
import com.uade.tpo.demo.repository.BillRepository;

@Service
public class BillServiceImpl implements BillService {

	
	@Autowires
	private OrderRepository orderRepository;

	@Autowires
	private UserRepository userRepository;
	
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
	public Page<Bill> getBillsByUserId(Long userId, Pageable pageable) throws NoBillWithUserId, NoUserIdException {
		if(userRepository.findById(userId).isEmpty()){ //si el usuario no existe
			throw new NoUserIdException();
		}
		Page<Bill> existingBills = billRepository.findByUserId(userId, pageable);
		if (existingBills.isEmpty()){
			throw new NoBillWithUserId();
		}
		return existingBills;
	}

	@Override
	public Optional<Bill> getBillsByOrderId(Long orderId) throws NoBillWithOrderId {
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
	public Bill createBill(Long orderId, Double precioTotal, LocalDate fecha) throws BillDuplicateException {
		if (billRepository.findByOrderId(orderId).isPresent()){ //si la factura ya existe
			throw new BillDuplicateException();
		}
		Order existingOrder = orderRepository.findById(orderId) //si la orden no existe, no se puede crear la factura
				.orElseThrow(() -> NoBillWithOrderId::new); //referencia a la construcción de la excepción
		Bill newBill = new Bill(existingOrder, precioTotal, fecha);
		return billRepository.save(newBill);
	}
}
