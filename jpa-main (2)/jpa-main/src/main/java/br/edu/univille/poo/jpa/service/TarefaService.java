package br.edu.univille.poo.jpa.service;

import br.edu.univille.poo.jpa.entities.Tarefa;
import br.edu.univille.poo.jpa.repository.TarefaRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository repository;

    public Tarefa insert(Tarefa tarefa){
        tarefa.setId(0);

        if(Strings.isBlank(tarefa.getTitulo())){
            throw new RuntimeException(("Título não informado."));
        }
        if(tarefa.getDataPrevista() == null){
            throw new RuntimeException(("Data prevista não informada."));
        }

        return repository.save(tarefa);
    }

    public List<Tarefa> getAll(){
        return repository.findAll();
    }

    public Optional<Tarefa> getById(Long id){
        return repository.findById(id);
    }


    public Tarefa update(Tarefa tarefa){
        Tarefa old  = repository.findById(tarefa.getId()).orElse(null);
        if (old == null){
            throw new RuntimeException("Tarefa não encontrada!");
        }

        old.setTitulo(tarefa.getTitulo());
        old.setDescricao(tarefa.getDescricao());
        old.setFinalizado(tarefa.isFinalizado());
        old.setDataPrevista(tarefa.getDataPrevista());
        old.setDataPrevista(tarefa.getDataPrevista());

        if(Strings.isBlank(tarefa.getTitulo())){
            throw new RuntimeException("Nome não informado.");
        }
        if(tarefa.getDataFinalizacao() == null){
            throw new RuntimeException("Data de finalização não informada.");
        }

        return repository.save(old);
    }

    public List<Tarefa> getAllUnfinished() {
        return repository.findByFinalizadoFalse();
    }

    public List<Tarefa> getAllFinished() {
        return repository.findByFinalizadoTrue();
    }

    public void delete(Tarefa tarefa){
        var old = repository.findById(tarefa.getId()).orElse(null);
        if (old == null){
            throw new RuntimeException("Tarefa não encontrada!");
        }
        repository.delete(old);
    }

    public List<Tarefa> getAllLate() {
        LocalDate today = LocalDate.now();
        return repository.findByDataPrevistaBeforeAndFinalizadoFalse(today);
    }

    public List<Tarefa> getByDateRange(LocalDate dateInitial, LocalDate dateFinal){
        return repository.findByDataPrevistaBetweenAndFinalizadoFalse(dateInitial, dateFinal);
    }

    public Boolean finishTaskById(Long id){
        Tarefa old  = repository.findById(id).orElse(null);
        old.setFinalizado(true);
        return repository.save(old).isFinalizado();
    }

}
