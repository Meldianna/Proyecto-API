package com.uade.tpo.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST , reason = "Ya existe un usuario con ese mail. Inicie sesión.")
public class DuplicateUserException extends RuntimeException{
    
}
