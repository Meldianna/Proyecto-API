package com.uade.tpo.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason =" No exise el tipo de delivery seleccionado. Intente nuevamente con uno válido. ")
public class NoSuchDeliveryTypeException extends RuntimeException{

    
}