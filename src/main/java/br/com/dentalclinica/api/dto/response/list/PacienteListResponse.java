package br.com.dentalclinica.api.dto.response.list;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class PacienteListResponse {
    private UUID id;
    private String nome;
}
