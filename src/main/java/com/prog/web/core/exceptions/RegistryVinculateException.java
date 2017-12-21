package com.prog.web.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class RegistryVinculateException extends RuntimeException {

    public RegistryVinculateException(String mensagem) {
        super(mensagem);
    }

}
