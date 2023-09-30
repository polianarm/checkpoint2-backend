package br.com.dentalclinica.domain.service.impl;

import br.com.dentalclinica.domain.entity.Consulta;
import br.com.dentalclinica.domain.exception.NotFoundException;
import br.com.dentalclinica.domain.repository.ConsultaRepository;
import br.com.dentalclinica.domain.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ConsultaServiceImpl implements ConsultaService {

    private final ConsultaRepository consultaRepository;
    @Autowired
    public ConsultaServiceImpl(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    @Override
    public Consulta criarConsulta(Consulta consulta) {
        return consultaRepository.save(consulta);
    }

    @Override
    public List<Consulta> buscarConsultas(String termo) {
        return consultaRepository.findAll();
    }

    @Override
    public Consulta buscarConsultaPorId(UUID id) {
        return consultaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    @Override
    public Consulta atualizarConsulta(UUID id, Consulta consulta) {

        try {
            consultaRepository.findById(id).orElseThrow();;
        } catch (Exception e) {
            throw new NotFoundException(id);
        }
        return consultaRepository.save(consulta);
    }

    @Override
    public void excluirConsulta(UUID id) {
        if (!consultaRepository.existsById(id)) {
        throw new NotFoundException(id);
    }
        consultaRepository.deleteById(id);;

    }
}

