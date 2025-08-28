package com.uade.tpo.demo.entity.dto;

import java.time.LocalDate;

import com.uade.tpo.demo.entity.Order;
import com.uade.tpo.demo.entity.User;

//import lombok.Data;

//@Data
public class BillRequest {

    private Long id;
    private Long idOrder;

    private double  precioTotal;
    private LocalDate date;

    public Long getIdOrder() {
        return idOrder;
    }
    public double getPrecioTotal() {
        return precioTotal;
    }
    public LocalDate getFecha() {
        return date;
    }
    public Long getId(){
        return id;
    }
}
