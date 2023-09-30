package br.com.dentalclinica.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "enderecos")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(length = 100, nullable = false)
    private String logradouro;
    @Column(length = 100, nullable = false)
    private String bairro;

    @Column(length = 100, nullable = false)
    private String cidade;
    @Column(length = 100, nullable = false)
    private String estado;
    @Column(length = 10, nullable = false)
    private String cep;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;
    @PrePersist
    public void naCriacao() {
        this.criadoEm = LocalDateTime.now();
    }
    @PreUpdate
    public void naAtualizacao() {
        this.atualizadoEm = LocalDateTime.now();
    }
}
