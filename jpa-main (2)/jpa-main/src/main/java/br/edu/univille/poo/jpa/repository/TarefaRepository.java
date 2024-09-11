package br.edu.univille.poo.jpa.repository;

import br.edu.univille.poo.jpa.entities.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    List<Tarefa> findByFinalizadoFalse();
    List<Tarefa> findByFinalizadoTrue();

    List<Tarefa> findByDataPrevistaBeforeAndFinalizadoFalse(LocalDate dataAtual);

    List<Tarefa> findByDataPrevistaBetweenAndFinalizadoFalse(LocalDate dataInicial, LocalDate dataFinal);
}
