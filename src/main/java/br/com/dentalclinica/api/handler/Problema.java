package br.com.dentalclinica.api.handler;

public record Problema(Integer status,
                       String message,
                       String detail) {
}
