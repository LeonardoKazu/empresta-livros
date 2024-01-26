
package com.leonardokazu.livraria.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@EqualsAndHashCode(of = "id")
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataEmprestada;
    private LocalDate dataDevolucao;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "livro_id")
    private Livro livro;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "leitor_id")
    private Leitor leitor;

    public Emprestimo(Livro livro, Leitor leitor) {
        this.livro = livro;
        this.leitor = leitor;
        this.dataEmprestada = LocalDate.now();
    }

    public void devolver(){
        this.dataDevolucao = LocalDate.now();
    }
}

