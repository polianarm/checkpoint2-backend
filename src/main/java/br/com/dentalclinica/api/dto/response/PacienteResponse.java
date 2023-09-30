package br.com.dentalclinica.api.dto.response;

import br.com.dentalclinica.domain.entity.GeneroEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class PacienteResponse {
    private UUID id;
    private String nome;
    private LocalDate dataNascimento;
    private EnderecoResponse endereco;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;
    private GeneroEnum genero;
    private ContatoResponse contato;

}