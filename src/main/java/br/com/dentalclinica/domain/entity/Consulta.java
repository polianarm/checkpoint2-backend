package br.com.dentalclinica.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "consultas")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "clinica_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_clinica_consulta"))
    private Clinica clinica;
    @ManyToOne
    @JoinColumn(name = "dentista_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_dentista_consulta"))
    private Dentista dentista;
    @ManyToOne
    @JoinColumn(name = "paciente_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_paciente_consulta"))
    private Paciente paciente;
    @Column(nullable = false)
    private LocalDate dataConsulta;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;
    @Column(nullable = false)
    private String descricao;
    @Column(nullable = false)
    private Boolean cancelada;
    @Column(nullable = false)
    private String motivoCancelamento;
    @PrePersist
    public void naCriacao() {
        this.criadoEm = LocalDateTime.now();
    }
    @PreUpdate
    public void naAtualizacao() {
        this.atualizadoEm = LocalDateTime.now();
    }
}
