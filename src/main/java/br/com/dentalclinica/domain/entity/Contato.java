package br.com.dentalclinica.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "contatos")
public class Contato {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Email
    @Column(length = 100)
    private String email;
    @Column(length = 15)
    private String telefone;
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
