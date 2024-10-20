package br.com.schoolify.shoolify.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Table(name = "tb_atividade")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Atividade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "id_professor_disciplina", nullable = false)
    private DiscProfTurma professorDisciplina;
    
    private String titulo;
    private LocalDateTime dataInicio;
    private LocalDateTime dataEntrega;
    
    @Column (columnDefinition = "TEXT")
    private String descricao;
}
