package br.com.dentalclinica.api.controller;

import br.com.dentalclinica.api.dto.request.ClinicaRequest;
import br.com.dentalclinica.api.dto.response.ClinicaResponse;
import br.com.dentalclinica.api.dto.response.ContatoResponse;
import br.com.dentalclinica.api.dto.response.EnderecoResponse;
import br.com.dentalclinica.api.dto.response.list.ClinicaListResponse;
import br.com.dentalclinica.api.dto.response.wrapper.ClinicaWrapperResponse;
import br.com.dentalclinica.domain.entity.Clinica;
import br.com.dentalclinica.domain.entity.Contato;
import br.com.dentalclinica.domain.entity.Endereco;
import br.com.dentalclinica.domain.service.ClinicaService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/clinicas")
public class ClinicaController {

    private final ClinicaService clinicaService;

    @Autowired
    public ClinicaController(ClinicaService clinicaService) {
        this.clinicaService = clinicaService;
    }

    //criar
    @PostMapping
    ResponseEntity<?> criarClinica(@RequestBody @Valid ClinicaRequest request) {
        Clinica clinica = new Clinica();
        clinica.setCnpj(request.getCnpj());
        clinica.setNome(request.getNome());
        clinica.setRazaoSocial(request.getRazaoSocial());
        clinica.setDescricao(request.getDescricao());

        Contato contato = new Contato();
        contato.setEmail(request.getContato().getEmail());
        contato.setTelefone(request.getContato().getTelefone());
        clinica.setContato(contato);

        Endereco endereco = new Endereco();
        endereco.setLogradouro(request.getEndereco().getLogradouro());
        endereco.setBairro(request.getEndereco().getBairro());
        endereco.setCidade(request.getEndereco().getCidade());
        endereco.setEstado(request.getEndereco().getEstado());
        endereco.setCep(request.getEndereco().getCep());
        clinica.setEndereco(endereco);

        Clinica clinicaCriada = clinicaService.criarClinica(clinica);
        return ResponseEntity.ok(clinicaCriada.getId());
    }

    //clinicaResponse
    private ClinicaResponse clinicaResponseByClinica(Clinica clinica) {
        ClinicaResponse clinicaResponse = new ClinicaResponse();
        clinicaResponse.setId(clinica.getId());
        clinicaResponse.setNome(clinica.getNome());
        clinicaResponse.setCnpj(clinica.getCnpj());
        clinicaResponse.setRazaoSocial(clinica.getRazaoSocial());
        clinicaResponse.setDescricao(clinica.getDescricao());
        clinicaResponse.setCriadoEm(clinica.getCriadoEm());
        clinicaResponse.setAtualizadoEm(clinica.getAtualizadoEm());

        ContatoResponse contato = new ContatoResponse();
        contato.setId(clinica.getContato().getId());
        contato.setEmail(clinica.getContato().getEmail());
        contato.setTelefone(clinica.getContato().getTelefone());
        clinicaResponse.setContato(contato);

        EnderecoResponse endereco = new EnderecoResponse();
        endereco.setId(clinica.getEndereco().getId());
        endereco.setLogradouro(clinica.getEndereco().getLogradouro());
        endereco.setBairro(clinica.getEndereco().getBairro());
        endereco.setCidade(clinica.getEndereco().getCidade());
        endereco.setEstado(clinica.getEndereco().getEstado());
        endereco.setCep(clinica.getEndereco().getCep());
        clinicaResponse.setEndereco(endereco);

        return clinicaResponse;
    }

    //BuscarPorId
    @GetMapping("{id}")
    ResponseEntity<ClinicaResponse> buscarPorId(@PathVariable UUID id) {
        Clinica clinica = clinicaService.buscarClinicaPorId(id);
        ClinicaResponse response = clinicaResponseByClinica(clinica);
        return ResponseEntity.ok(response);
    }

    //BuscarTodosPorTermo
    @GetMapping
    ResponseEntity<ClinicaWrapperResponse> buscarTodosPorTermo(@RequestParam String termo) {
        List<Clinica> clinicas = clinicaService.buscarClinicas(termo);
        ClinicaWrapperResponse clinicaWrapperResponse = new ClinicaWrapperResponse();
        clinicaWrapperResponse.setClinicas(clinicas.stream().map(clinica -> {
            ClinicaListResponse clinicaListResponse = new ClinicaListResponse();
            clinicaListResponse.setId(clinica.getId());
            clinicaListResponse.setNome(clinica.getNome());
            clinicaListResponse.setCnpj(clinica.getCnpj());
            return clinicaListResponse;
        }).toList());
        return ResponseEntity.ok(clinicaWrapperResponse);
    }

    //atualizar
    @PutMapping({"id"})
    ResponseEntity<ClinicaResponse> atualizarClinica(@PathVariable UUID id, @RequestBody @Valid ClinicaRequest request) {

        Clinica clinica = clinicaService.buscarClinicaPorId(id);
        clinica.setCnpj(request.getCnpj());
        clinica.setNome(request.getNome());
        clinica.setRazaoSocial(request.getRazaoSocial());
        clinica.setDescricao(request.getDescricao());

        Contato contato = clinica.getContato();
        contato.setEmail(request.getContato().getEmail());
        contato.setTelefone(request.getContato().getTelefone());
        clinica.setContato(contato);

        Endereco endereco = clinica.getEndereco();
        endereco.setLogradouro(request.getEndereco().getLogradouro());
        endereco.setBairro(request.getEndereco().getBairro());
        endereco.setCidade(request.getEndereco().getCidade());
        endereco.setEstado(request.getEndereco().getEstado());
        endereco.setCep(request.getEndereco().getCep());
        clinica.setEndereco(endereco);

        Clinica clinicaAtualizada = clinicaService.atualizarClinica(id, clinica);
        ClinicaResponse response = clinicaResponseByClinica(clinicaAtualizada);
        return ResponseEntity.ok(response);
    }

    //deletar
    @DeleteMapping
    ResponseEntity<Void> excluirClinica(@PathVariable UUID id) {
        clinicaService.excluirClinica(id);
        return ResponseEntity.ok().build(); }

}

