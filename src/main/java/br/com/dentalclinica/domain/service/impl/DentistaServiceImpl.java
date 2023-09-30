package br.com.dentalclinica.domain.service.impl;

import br.com.dentalclinica.domain.entity.Dentista;
import br.com.dentalclinica.domain.exception.BadRequestContatoNuloException;
import br.com.dentalclinica.domain.exception.NotFoundException;
import br.com.dentalclinica.domain.repository.DentistaRepository;
import br.com.dentalclinica.domain.service.DentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DentistaServiceImpl implements DentistaService {

    private final DentistaRepository dentistaRepository;

    @Autowired
    public DentistaServiceImpl(DentistaRepository dentistaRepository) {
        this.dentistaRepository = dentistaRepository;
    }

    @Override
    public Dentista criarDentista(Dentista dentista) {

        if(dentista.getContato().getEmail() == null && dentista.getContato().getTelefone() == null) {
            throw new BadRequestContatoNuloException();
        }
        return dentistaRepository.save(dentista);
    }

    @Override
    public List<Dentista> buscarDentistas(String termo) {
        return dentistaRepository.findByNomeStartingWith(termo);
    }

    @Override
    public Dentista buscarDentistaPorId(UUID id) {
        return dentistaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    @Override
    public Dentista atualizarDentista(UUID id, Dentista dentista) {

        try {
            dentistaRepository.findById(id).orElseThrow();;
        } catch (Exception e) {
            throw new NotFoundException(id);
        }
        return dentistaRepository.save(dentista);
    }


    @Override
    public void excluirDentista(UUID id) {
        if (!dentistaRepository.existsById(id)) {
            throw new NotFoundException(id);
        }
        dentistaRepository.deleteById(id);
    }

}

