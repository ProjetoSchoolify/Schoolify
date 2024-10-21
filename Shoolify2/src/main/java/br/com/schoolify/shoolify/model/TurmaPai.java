package br.com.schoolify.shoolify.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_turmapai")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TurmaPai {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    private Set<Usuario> usuarios = new HashSet<>();

    @ManyToMany
    private Set<Turma> turmas = new HashSet<>();
    
}
