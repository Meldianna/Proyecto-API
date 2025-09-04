package com.uade.tpo.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(code= HttpStatus.NOT_FOUND, reason="No se ha encontrado el recurso solicitado.")
public class ResourceNotFoundException extends Exception{
    
}
