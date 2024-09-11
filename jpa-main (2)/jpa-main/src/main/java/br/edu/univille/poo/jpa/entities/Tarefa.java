package br.edu.univille.poo.jpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity

public class Tarefa {

    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private String titulo;
    private String descricao;
    private boolean finalizado;
    @Column(nullable = false)
    private LocalDate dataPrevista;
    private LocalDate dataFinalizacao;
}

