package br.com.dentalclinica.api.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ContatoResponse {
    private UUID id;
    private String email;
    private String telefone;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;

}