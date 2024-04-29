package com.gft.peopleservice.exceptions.handle;

import com.gft.peopleservice.exceptions.PessoaNaoEncontradaException;
import com.gft.peopleservice.feign.exceptions.RecursoNaoEncontradoException;
import com.gft.peopleservice.feign.exceptions.ServiceIndisponivelException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(PessoaNaoEncontradaException.class)
    public ResponseEntity<CustomError> pessoaNaoEncontrada(PessoaNaoEncontradaException exception, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError error = new CustomError(Instant.now(), status.value(), exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<CustomError> recursoNaoEncontrado(RecursoNaoEncontradoException exception, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError error = new CustomError(Instant.now(), status.value(), exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(ServiceIndisponivelException.class)
    public ResponseEntity<CustomError> servicoIndisponivel(ServiceIndisponivelException exception, HttpServletRequest request) {
        HttpStatus status = HttpStatus.SERVICE_UNAVAILABLE;
        CustomError error = new CustomError(Instant.now(), status.value(), exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }
}
