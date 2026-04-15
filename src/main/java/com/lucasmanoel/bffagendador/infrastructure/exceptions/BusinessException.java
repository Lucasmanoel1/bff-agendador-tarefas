package com.lucasmanoel.bffagendador.infrastructure.exceptions;

public class BusinessException extends RuntimeException {

    public BusinessException (String mensagem, Throwable throwable){
        super(mensagem, throwable);
    }
    public BusinessException (String mensagem){
        super(mensagem);
    }
}
