package br.com.dentalclinica.domain.service;

import br.com.dentalclinica.domain.entity.Consulta;

import java.util.List;
import java.util.UUID;

public interface ConsultaService {

    Consulta criarConsulta(Consulta consulta);

    List<Consulta> buscarConsultas(String termo);

    Consulta buscarConsultaPorId(UUID id);

    Consulta atualizarConsulta(UUID id, Consulta consulta);

    void excluirConsulta(UUID id);

}
