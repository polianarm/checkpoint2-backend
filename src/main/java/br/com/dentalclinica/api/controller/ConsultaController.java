package br.com.dentalclinica.api.controller;

import br.com.dentalclinica.api.dto.request.ClinicaRequest;
import br.com.dentalclinica.api.dto.request.ConsultaRequest;
import br.com.dentalclinica.api.dto.response.ClinicaResponse;
import br.com.dentalclinica.api.dto.response.ConsultaResponse;
import br.com.dentalclinica.api.dto.response.list.ConsultaListResponse;
import br.com.dentalclinica.api.dto.response.wrapper.ConsultaWrapperResponse;
import br.com.dentalclinica.domain.entity.Clinica;
import br.com.dentalclinica.domain.entity.Consulta;
import br.com.dentalclinica.domain.entity.Contato;
import br.com.dentalclinica.domain.entity.Endereco;
import br.com.dentalclinica.domain.service.ConsultaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/consultas")
public class ConsultaController {

    private final ConsultaService consultaService;
    private final ObjectMapper objectMapper;

    @Autowired
    public ConsultaController(ConsultaService consultaService, ObjectMapper objectMapper) {
        this.consultaService = consultaService;
        this.objectMapper = objectMapper;
    }

    @PostMapping
    ResponseEntity<?> criarConsulta (@RequestBody @Valid ConsultaRequest request) {
        Consulta consulta = objectMapper.convertValue(request, Consulta.class);
        Consulta consulta1 = consultaService.criarConsulta(consulta);
        return ResponseEntity.ok(consulta1);
    }

    private ConsultaResponse consultaResponseByConsulta(Consulta consulta) {
        ConsultaResponse consultaResponse = new ConsultaResponse();
        consultaResponse.setId(consulta.getId());
        consultaResponse.setClinica(consultaResponse.getClinica());
        consultaResponse.setDentista(consultaResponse.getDentista());
        consultaResponse.setPaciente(consultaResponse.getPaciente());
        consultaResponse.setDataConsulta(consulta.getDataConsulta());
        consultaResponse.setCriadoEm(consulta.getCriadoEm());
        consultaResponse.setAtualizadoEm(consulta.getAtualizadoEm());
        consultaResponse.setDescricao(consulta.getDescricao());
        consultaResponse.setCancelada(consulta.getCancelada());
        consultaResponse.setMotivoCancelamento(consulta.getMotivoCancelamento());

        return consultaResponse;
    }

    @GetMapping("{id}")
    ResponseEntity<ConsultaResponse> buscarPorId(@PathVariable UUID id) {
        Consulta consulta = consultaService.buscarConsultaPorId(id);
        ConsultaResponse response = consultaResponseByConsulta(consulta);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    ResponseEntity<ConsultaWrapperResponse> buscarTodosPorTermo(@RequestParam String termo) {
        List<Consulta> consultas = consultaService.buscarConsultas(termo);
        ConsultaWrapperResponse consultaWrapperResponse = new ConsultaWrapperResponse();
        consultaWrapperResponse.setConsultas(consultas.stream().map(consulta -> {
            ConsultaListResponse consultaListResponse = new ConsultaListResponse();
            consultaListResponse.setId(consulta.getId());
            consultaListResponse.setDataConsulta(consulta.getDataConsulta());
            return consultaListResponse;
        }).toList());
        return ResponseEntity.ok(consultaWrapperResponse);
    }

    @PutMapping({"id"})
    ResponseEntity<ConsultaResponse> atualizarConsulta(@PathVariable UUID id, @RequestBody @Valid ConsultaRequest request) {
        Consulta consulta = objectMapper.convertValue(request, Consulta.class);
        Consulta consultaAtualizada = consultaService.atualizarConsulta(id, consulta);
        ConsultaResponse response = objectMapper.convertValue(consultaAtualizada, ConsultaResponse.class);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    ResponseEntity<Void> excluirConsulta(@PathVariable UUID id) {
        consultaService.excluirConsulta(id);
        return ResponseEntity.ok().build(); }

}

