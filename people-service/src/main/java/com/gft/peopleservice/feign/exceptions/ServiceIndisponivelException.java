package com.gft.peopleservice.feign.exceptions;

public class ServiceIndisponivelException extends RuntimeException {

    public ServiceIndisponivelException(String message) {
        super(message);
    }

}
