package br.com.dentalclinica.api.dto.request;

import br.com.dentalclinica.domain.entity.Clinica;
import br.com.dentalclinica.domain.entity.EspecialidadeEnum;
import br.com.dentalclinica.domain.entity.GeneroEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class DentistaRequest {
    @NotBlank
    private String nome;
    @NotNull
    private LocalDate dataNascimento;
    @NotNull
    private EspecialidadeEnum especialidade;
    @NotNull
    private GeneroEnum genero;
    @NotNull
    private ContatoRequest contato;
    @NotEmpty
    private Set<Clinica> clinicasDentistas;
}

