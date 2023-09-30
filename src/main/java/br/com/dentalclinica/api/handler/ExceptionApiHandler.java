package br.com.dentalclinica.api.handler;

import br.com.dentalclinica.domain.exception.BadRequestCnpjException;
import br.com.dentalclinica.domain.exception.BadRequestContatoNuloException;
import br.com.dentalclinica.domain.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionApiHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Problema> notFoundExceptionHandler(NotFoundException e) {
        Problema problema = new Problema(HttpStatus.NOT_FOUND.value(),
                "Algum problema aconteceu", e.getMessage());
        return ResponseEntity.ok().body(problema);
    }

    @ExceptionHandler(BadRequestCnpjException.class)
    public ResponseEntity<Problema> badRequestCnpjException(BadRequestCnpjException e) {
        Problema problema = new Problema(HttpStatus.BAD_REQUEST.value(),
                "Algum problema aconteceu", e.getMessage());
        return ResponseEntity.ok().body(problema);
    }

    @ExceptionHandler(BadRequestContatoNuloException.class)
    public ResponseEntity<Problema> badRequestContatoNuloException(BadRequestContatoNuloException e) {
        Problema problema = new Problema(HttpStatus.BAD_REQUEST.value(),
                "Algum problema aconteceu", e.getMessage());
        return ResponseEntity.ok().body(problema);
    }

}
