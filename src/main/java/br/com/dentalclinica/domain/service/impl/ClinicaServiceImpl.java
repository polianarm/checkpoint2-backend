package br.com.dentalclinica.domain.service.impl;

import br.com.dentalclinica.domain.entity.Clinica;
import br.com.dentalclinica.domain.exception.BadRequestCnpjException;
import br.com.dentalclinica.domain.exception.BadRequestContatoNuloException;
import br.com.dentalclinica.domain.exception.NotFoundException;
import br.com.dentalclinica.domain.repository.ClinicaRepository;
import br.com.dentalclinica.domain.service.ClinicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClinicaServiceImpl implements ClinicaService {

    private final ClinicaRepository clinicaRepository;
    @Autowired
    public ClinicaServiceImpl(ClinicaRepository clinicaRepository) {
        this.clinicaRepository = clinicaRepository;
    }

    @Override
    public Clinica criarClinica(Clinica clinica) {
        boolean cnpjExiste = clinicaRepository.existsByCnpj(clinica.getCnpj());
        if (cnpjExiste){
            throw new BadRequestCnpjException(clinica.getCnpj());
        }

        if(clinica.getContato().getEmail() == null && clinica.getContato().getTelefone() == null) {
            throw new BadRequestContatoNuloException();
        }

        return clinicaRepository.save(clinica);
    }

    @Override
    public List<Clinica> buscarClinicas(String termo) {
        return clinicaRepository.findByNomeStartingWith(termo);
    }

    @Override
    public Clinica buscarClinicaPorId(UUID id) {
        return clinicaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    @Override
    public Clinica atualizarClinica(UUID id, Clinica clinica) {

        try {
            clinicaRepository.findById(id).orElseThrow();;
        } catch (Exception e) {
            throw new NotFoundException(id);
        }
        return clinicaRepository.save(clinica);
    }

    @Override
    public void excluirClinica(UUID id) {

        if (!clinicaRepository.existsById(id)) {
            throw new NotFoundException(id);
        }
        clinicaRepository.deleteById(id);
    }
}

