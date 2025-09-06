package com.uade.tpo.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason =" No exise la categoría seleccionada. Intente nuevamente con una válida. ")
public class NoSuchCategoryException extends RuntimeException{
    
}
