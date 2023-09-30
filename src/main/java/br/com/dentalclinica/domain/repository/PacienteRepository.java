package br.com.dentalclinica.domain.repository;

import br.com.dentalclinica.domain.entity.Dentista;
import br.com.dentalclinica.domain.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, UUID> {
    boolean existsById(UUID id);
    List<Paciente> findByNomeStartingWith(String termo);
}
