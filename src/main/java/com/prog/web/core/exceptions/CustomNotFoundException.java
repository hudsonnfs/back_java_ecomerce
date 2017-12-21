package com.prog.web.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CustomNotFoundException extends RuntimeException {

    /**
     * Construtor da classe.
     *
     * @param mensagem
     */
    public CustomNotFoundException(String mensagem) {
        super(mensagem);
    }

}
