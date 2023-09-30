package br.com.dentalclinica.domain.repository;

import br.com.dentalclinica.domain.entity.Clinica;
import br.com.dentalclinica.domain.entity.Dentista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DentistaRepository extends JpaRepository<Dentista, UUID> {
    boolean existsById(UUID id);
    List<Dentista> findByNomeStartingWith(String termo);
}
