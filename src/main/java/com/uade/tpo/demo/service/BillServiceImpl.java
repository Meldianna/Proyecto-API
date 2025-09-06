package com.uade.tpo.demo.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uade.tpo.demo.entity.Bill;
import com.uade.tpo.demo.entity.Order;
import com.uade.tpo.demo.entity.dto.BillResponse;
import com.uade.tpo.demo.exceptions.BillDuplicateException;
import com.uade.tpo.demo.exceptions.NoBillWithDateException;
import com.uade.tpo.demo.exceptions.NoBillWithOrderId;
import com.uade.tpo.demo.exceptions.NoBillWithUserId;
import com.uade.tpo.demo.exceptions.NoUserIdException;
import com.uade.tpo.demo.repository.BillRepository;
import com.uade.tpo.demo.repository.OrderRepository;
import com.uade.tpo.demo.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class BillServiceImpl implements BillService {

	
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private UserRepository userRepository;
	
    @Autowired
    private BillRepository billRepository;

	@Override
	public Page<BillResponse> getBills(PageRequest pageable) {
		return billRepository.findAll(pageable)
		.map(this::toBillResponse); //este metodo permite devolver una lista de facturas
    }
	@Override
	public Optional<BillResponse> getBillsById(Long billId){
		return billRepository.findById(billId)
		.map(this::toBillResponse);
	}
	
	@Override
	public Page<BillResponse> getBillsByUserId(Long userId, Pageable pageable) throws NoBillWithUserId, NoUserIdException {
		if(userRepository.findById(userId).isEmpty()){ //si el usuario no existe
			throw new NoUserIdException();
		}
		Page<Bill> existingBills = billRepository.findByUserId(userId, pageable);
		if (existingBills.isEmpty()){
			throw new NoBillWithUserId();
		}
		return existingBills.map(this::toBillResponse); //mapea la página a BillResponse
	}

	@Override
	public Optional<BillResponse> getBillsByOrderId(Long orderId) throws NoBillWithOrderId {
		Optional<Bill> existingBill = billRepository.findByOrderId(orderId);
		if (!existingBill.isPresent()){
			throw new NoBillWithOrderId();
		}
		return existingBill.map(this::toBillResponse);
	}
	@Override
	public Page<BillResponse> getBillsByDate(LocalDate date, Pageable pageable) throws NoBillWithDateException {
		Page<Bill> existingBills = billRepository.findByDate(date, pageable);
		if (!existingBills.hasContent()){
			throw new NoBillWithDateException();
		}
		return billRepository.findByDate(date, pageable).map(this::toBillResponse); //mapeo a Bill Response
	}


	@Override
	@Transactional
	public Bill createBill(Long orderId, Double precioTotal, LocalDate fecha) throws BillDuplicateException, NoBillWithOrderId {
		if (billRepository.findByOrderId(orderId).isPresent()){ //si la factura ya existe
			throw new BillDuplicateException();
		}
		//corregir exception
		Order existingOrder = orderRepository.findById(orderId) //si la orden no existe, no se puede crear la factura
				.orElseThrow(() -> new NoBillWithOrderId()); //referencia a la construcción de la excepción
		Bill newBill = new Bill(existingOrder, precioTotal, fecha);
		return billRepository.save(newBill);
	}

	public BillResponse toBillResponse(Bill bill){
		return new BillResponse(bill.getId(), bill.getOrder().getId(), bill.getPrecioTotal(), bill.getDate());
	}
}
