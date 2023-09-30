package br.com.dentalclinica.api.dto.response;

import br.com.dentalclinica.domain.entity.Clinica;
import br.com.dentalclinica.domain.entity.Dentista;
import br.com.dentalclinica.domain.entity.Paciente;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ConsultaResponse {
    private UUID id;
    private Clinica clinica;
    private Dentista dentista;
    private Paciente paciente;
    private LocalDate dataConsulta;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;
    private String descricao;
    private boolean cancelada;
    private String motivoCancelamento;
}
