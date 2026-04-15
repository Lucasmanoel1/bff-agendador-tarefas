package com.lucasmanoel.bffagendador.infrastructure.client.config;

import com.lucasmanoel.bffagendador.infrastructure.exceptions.BusinessException;
import com.lucasmanoel.bffagendador.infrastructure.exceptions.ConflictExeception;
import com.lucasmanoel.bffagendador.infrastructure.exceptions.ResourceNotFoundException;
import com.lucasmanoel.bffagendador.infrastructure.exceptions.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignError implements ErrorDecoder {


    @Override
    public Exception decode(String s, Response response) {
        switch (response.status()){
            case 409:
                return new ConflictExeception("Erro atributo já existente");

            case 403:
                return new ResourceNotFoundException("Erro atributo não encontrado");

            case 401:
                return new UnauthorizedException("Erro usuário não autorizado");

            default:
                return new BusinessException("Erro de servidor");
        }
    }
}
