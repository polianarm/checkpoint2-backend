package br.com.dentalclinica.domain.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class BadRequestContatoNuloException extends RuntimeException {
    public BadRequestContatoNuloException() {
        super("É necessário fornecer pelo menos um contato (telefone ou email)");
    }
}
