package br.com.dentalclinica.domain.exception;

public class BadRequestCnpjException extends RuntimeException {

    public BadRequestCnpjException(String cnpj) {
        super(String.format("CNPJ inválido: %s. Verifique o formato do CNPJ inserido.", cnpj));
    }
}

