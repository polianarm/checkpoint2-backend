package br.com.dentalclinica.api.dto.request;

import br.com.dentalclinica.domain.entity.Clinica;
import br.com.dentalclinica.domain.entity.Dentista;
import br.com.dentalclinica.domain.entity.Paciente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ConsultaRequest {
    @NotBlank
    private LocalDate dataConsulta;
    @NotNull
    private String descricao;
    @NotNull
    private boolean cancelada;
    @NotNull
    private String motivoCancelamento;
    @NotNull
    private Paciente paciente;
    @NotNull
    private Dentista dentista;
    @NotNull
    private Clinica clinica;
}
