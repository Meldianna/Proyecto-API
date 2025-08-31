package com.uade.tpo.demo.entity.dto;

import java.time.LocalDate;

import lombok.Data;
@Data
public class BillResponse {
    private Long id;
    private double precioTotal;
    private LocalDate date;
    private Long idOrder;

    public BillResponse(Long id, Long idOrder, double precioTotal, LocalDate date) {
        this.id = id;
        this.precioTotal = precioTotal;
        this.date = date;
        this.idOrder = idOrder;
    }

}
