package br.com.dentalclinica.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "clinicas")
public class Clinica {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String nome;
    @Column(length = 20, unique = true)
    private String cnpj;
    @Column(nullable = false)
    @Size(min = 5)
    private String razaoSocial;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;
    @Column(nullable = false)
    private String descricao;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_endereco_clinica"))
    private Endereco endereco;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contato_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_contato_clinica"))
    private Contato contato;

    @PrePersist
    public void naCriacao() {
        this.criadoEm = LocalDateTime.now();
    }
    @PreUpdate
    public void naAtualizacao() {
        this.atualizadoEm = LocalDateTime.now();
    }

}
