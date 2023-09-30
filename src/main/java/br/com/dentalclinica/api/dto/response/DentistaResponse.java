package br.com.dentalclinica.api.dto.response;

import br.com.dentalclinica.domain.entity.EspecialidadeEnum;
import br.com.dentalclinica.domain.entity.GeneroEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class DentistaResponse {
    private UUID id;
    private String nome;
    private LocalDate dataNascimento;
    private EspecialidadeEnum especialidade;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;
    private GeneroEnum genero;
    private ContatoResponse contato;
}
