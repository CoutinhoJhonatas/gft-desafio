package com.gft.peopleservice.feign.exceptions;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.coyote.BadRequestException;

public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        return switch (response.status()) {
            case 400 -> new BadRequestException();
            case 404 -> new RecursoNaoEncontradoException("Recurso não encontrado");
            case 503 -> new ServiceIndisponivelException("Serviço indisponivel");
            default -> new Exception("Erro inesperado do servidor");
        };
    }

}
