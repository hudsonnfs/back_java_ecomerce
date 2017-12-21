package com.prog.web.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Lança exceçao para valores duplicados no sistema.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DuplicatedException extends RuntimeException {

    /**
     * Cosntrutor da classe.
     *
     * @param mensagem
     */
    public DuplicatedException(String mensagem) {
        super(mensagem);
    }

}
