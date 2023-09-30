package br.com.dentalclinica.api.controller;

import br.com.dentalclinica.api.dto.request.DentistaRequest;
import br.com.dentalclinica.api.dto.response.ContatoResponse;
import br.com.dentalclinica.api.dto.response.DentistaResponse;
import br.com.dentalclinica.api.dto.response.list.DentistaListResponse;
import br.com.dentalclinica.api.dto.response.wrapper.DentistaWrapperResponse;
import br.com.dentalclinica.domain.entity.Dentista;
import br.com.dentalclinica.domain.service.DentistaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/dentistas")
public class DentistaController {

    private final DentistaService dentistaService;
    private final ObjectMapper objectMapper;

    @Autowired
    public DentistaController(DentistaService dentistaService, ObjectMapper objectMapper) {
        this.dentistaService = dentistaService;
        this.objectMapper = objectMapper;
    }

    @PostMapping
    ResponseEntity<?> criarDentista(@RequestBody @Valid DentistaRequest request) {
        Dentista dentista = objectMapper.convertValue(request, Dentista.class);
        Dentista dentista1 = dentistaService.criarDentista(dentista);
        return ResponseEntity.ok(dentista1);
    }

    private DentistaResponse dentistaResponseByDentista(Dentista dentista) {
        DentistaResponse dentistaResponse = new DentistaResponse();
        dentistaResponse.setId(dentista.getId());
        dentistaResponse.setNome(dentista.getNome());
        dentistaResponse.setDataNascimento(dentista.getDataNascimento());
        dentistaResponse.setEspecialidade(dentista.getEspecialidade());
        dentistaResponse.setCriadoEm(dentista.getCriadoEm());
        dentistaResponse.setAtualizadoEm(dentista.getAtualizadoEm());
        dentistaResponse.setGenero(dentista.getGenero());

        ContatoResponse contato = new ContatoResponse();
        contato.setId(dentista.getContato().getId());
        contato.setEmail(dentista.getContato().getEmail());
        contato.setTelefone(dentista.getContato().getTelefone());
        dentistaResponse.setContato(contato);

        return dentistaResponse;
    }

    @GetMapping("{id}")
    ResponseEntity<DentistaResponse> buscarPorId(@PathVariable UUID id){
        Dentista dentista = dentistaService.buscarDentistaPorId(id);
        DentistaResponse response = dentistaResponseByDentista(dentista);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    ResponseEntity<DentistaWrapperResponse> buscarTodosPorTermo(@RequestParam String termo) {
        List<Dentista> dentistas = dentistaService.buscarDentistas(termo);
        DentistaWrapperResponse dentistaWrapperResponse = new DentistaWrapperResponse();
        dentistaWrapperResponse.setDentistas(dentistas.stream().map(dentista -> {
            DentistaListResponse dentistaListResponse = new DentistaListResponse();
            dentistaListResponse.setId(dentista.getId());
            dentistaListResponse.setNome(dentista.getNome());
            return dentistaListResponse;
        }).toList());
        return ResponseEntity.ok(dentistaWrapperResponse);
    }

    @PutMapping({"id"})
    ResponseEntity<DentistaResponse> atualizarDentista(@PathVariable UUID id, @RequestBody @Valid DentistaRequest request) {
        Dentista dentista = objectMapper.convertValue(request, Dentista.class);
        Dentista dentistaAtualizado = dentistaService.atualizarDentista(id, dentista);
        DentistaResponse response = objectMapper.convertValue(dentistaAtualizado, DentistaResponse.class);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    ResponseEntity<Void> excluirDentista(@PathVariable UUID id) {
        dentistaService.excluirDentista(id);
        return ResponseEntity.ok().build(); }

}



