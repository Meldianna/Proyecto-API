package com.uade.tpo.demo.entity.dto;

import java.time.LocalDate;

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
