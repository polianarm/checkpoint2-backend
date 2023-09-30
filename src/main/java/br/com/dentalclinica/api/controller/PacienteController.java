package br.com.dentalclinica.api.controller;

import br.com.dentalclinica.api.dto.request.PacienteRequest;
import br.com.dentalclinica.api.dto.response.ContatoResponse;
import br.com.dentalclinica.api.dto.response.EnderecoResponse;
import br.com.dentalclinica.api.dto.response.PacienteResponse;
import br.com.dentalclinica.api.dto.response.list.PacienteListResponse;
import br.com.dentalclinica.api.dto.response.wrapper.PacienteWrapperResponse;
import br.com.dentalclinica.domain.entity.Paciente;
import br.com.dentalclinica.domain.service.PacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;
    private final ObjectMapper objectMapper;

    @Autowired
    public PacienteController(PacienteService pacienteService, ObjectMapper objectMapper) {
        this.pacienteService = pacienteService;
        this.objectMapper = objectMapper;
    }

    @PostMapping
    ResponseEntity<?> criarPaciente(@RequestBody @Valid PacienteRequest request) {
        Paciente paciente = objectMapper.convertValue(request, Paciente.class);
        Paciente paciente1 = pacienteService.criarPaciente(paciente);
        return ResponseEntity.ok(paciente1);
    }

    private PacienteResponse pacienteResponseByPaciente(Paciente paciente) {
        PacienteResponse pacienteResponse = new PacienteResponse();
        pacienteResponse.setId(paciente.getId());
        pacienteResponse.setNome(paciente.getNome());
        pacienteResponse.setDataNascimento(paciente.getDataNascimento());
        pacienteResponse.setGenero(paciente.getGenero());
        pacienteResponse.setCriadoEm(paciente.getCriadoEm());
        pacienteResponse.setAtualizadoEm(paciente.getAtualizadoEm());

        ContatoResponse contato = new ContatoResponse();
        contato.setId(paciente.getContato().getId());
        contato.setEmail(paciente.getContato().getEmail());
        contato.setTelefone(paciente.getContato().getTelefone());
        pacienteResponse.setContato(contato);

        EnderecoResponse endereco = new EnderecoResponse();
        endereco.setId(paciente.getEndereco().getId());
        endereco.setLogradouro(paciente.getEndereco().getLogradouro());
        endereco.setBairro(paciente.getEndereco().getBairro());
        endereco.setCidade(paciente.getEndereco().getCidade());
        endereco.setEstado(paciente.getEndereco().getEstado());
        endereco.setCep(paciente.getEndereco().getCep());
        pacienteResponse.setEndereco(endereco);

        return pacienteResponse;
    }

    @GetMapping("{id}")
    ResponseEntity<PacienteResponse> buscarPorId(@PathVariable UUID id) {
        Paciente paciente = pacienteService.buscarPacientePorId(id);
        PacienteResponse response = pacienteResponseByPaciente(paciente);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    ResponseEntity<PacienteWrapperResponse> buscarTodosPorTermo(@RequestParam String termo) {
        List<Paciente> pacientes = pacienteService.buscarPacientes(termo);
        PacienteWrapperResponse pacienteWrapperResponse = new PacienteWrapperResponse();
        pacienteWrapperResponse.setPacientes(pacientes.stream().map(paciente -> {
            PacienteListResponse pacienteListResponse = new PacienteListResponse();
            pacienteListResponse.setId(paciente.getId());
            pacienteListResponse.setNome(paciente.getNome());
            return pacienteListResponse;
        }).toList());
        return ResponseEntity.ok(pacienteWrapperResponse);
    }

    @PutMapping({"id"})
    ResponseEntity<PacienteResponse> atualizarPaciente(@PathVariable UUID id, @RequestBody @Valid PacienteRequest request) {
        Paciente paciente = objectMapper.convertValue(request, Paciente.class);
        Paciente pacienteAtualizado = pacienteService.atualizarPaciente(id, paciente);
        PacienteResponse response = objectMapper.convertValue(pacienteAtualizado, PacienteResponse.class);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    ResponseEntity<Void> excluirPaciente(@PathVariable UUID id) {
        pacienteService.excluirPaciente(id);
        return ResponseEntity.ok().build(); }
}



