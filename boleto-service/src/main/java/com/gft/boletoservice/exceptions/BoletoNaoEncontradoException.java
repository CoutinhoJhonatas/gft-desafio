package com.gft.boletoservice.exceptions;

public class BoletoNaoEncontradoException extends RuntimeException {

    public BoletoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

}
