package com.uade.tpo.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND, reason="El producto que se consulta no existe o fue eliminado.")
public class NoSuchProductException extends Exception{
    
}
