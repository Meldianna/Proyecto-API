package com.uade.tpo.demo.entity.dto;

import java.time.LocalDate;

import com.uade.tpo.demo.entity.Order;
import com.uade.tpo.demo.entity.User;

//import lombok.Data;

//@Data
public class BillRequest {

    private Long id;
    private Order idOrden;

    private double  precioTotal;
    private LocalDate fecha;

    public Order getIdOrden() {
        return idOrden;
    }
    public double getPrecioTotal() {
        return precioTotal;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public Long getId(){
        return id;
    }
}
