package com.uade.tpo.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No se pudo encontrar el carrito. Puede ser que el usuario haya sido dado de baja")
public class NoCartForThatUserException extends RuntimeException{
    
}
