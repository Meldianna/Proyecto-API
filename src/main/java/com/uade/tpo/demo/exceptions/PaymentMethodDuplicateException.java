package com.uade.tpo.demo.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "La Orden que se intenta agregar esta duplicada")
public  class PaymentMethodDuplicateException extends Exception {
	
}
