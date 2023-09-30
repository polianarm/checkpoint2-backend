package br.com.dentalclinica.api.dto.request;

import br.com.dentalclinica.domain.entity.GeneroEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PacienteRequest {
    @NotBlank
    private String nome;
    private LocalDate dataNascimento;
    @NotNull
    private EnderecoRequest endereco;
    @NotNull
    private GeneroEnum genero;
    @NotNull
    private ContatoRequest contato;
}
