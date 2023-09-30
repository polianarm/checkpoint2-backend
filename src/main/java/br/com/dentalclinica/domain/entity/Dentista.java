package br.com.dentalclinica.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "dentistas")
public class Dentista {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private LocalDate dataNascimento;
    private EspecialidadeEnum especialidade;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;
    private GeneroEnum genero;
    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "contato_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name= "fk_contato_dentista"))
    private Contato contato;

    @ManyToMany
    @JoinTable (
            name = "clinicaDentista",
            joinColumns = @JoinColumn(name = "dentista_id",
                    foreignKey = @ForeignKey(name="fk_dentista_clinica")),
            inverseJoinColumns = @JoinColumn(name = "clinica_id",
                    foreignKey = @ForeignKey(name = "fk_clinica_dentista"))
    )
    private Set<Clinica> clinicasDentistas;
    @PrePersist
    public void naCriacao() {
        this.criadoEm = LocalDateTime.now();
    }
    @PreUpdate
    public void naAtualizacao() {
        this.atualizadoEm = LocalDateTime.now();
    }
}