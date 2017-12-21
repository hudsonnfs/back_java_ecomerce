package com.prog.web.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "Parametros informados invalidos.")
public class UnprocessableEntityException extends RuntimeException { }
