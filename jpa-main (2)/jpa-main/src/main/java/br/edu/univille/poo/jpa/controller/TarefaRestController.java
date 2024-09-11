package br.edu.univille.poo.jpa.controller;

import br.edu.univille.poo.jpa.entities.Tarefa;
import br.edu.univille.poo.jpa.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tarefas")

public class TarefaRestController {
    @Autowired
    private TarefaService service;

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody Tarefa tarefa) {
        try {
            tarefa = service.insert(tarefa);
            return new ResponseEntity<>(tarefa, HttpStatus.CREATED);

        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public List<Tarefa> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> getById(@PathVariable Long id) {
        var optional = service.getById(id);
        return optional.map(tarefa -> new ResponseEntity<>(tarefa, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PutMapping
    public ResponseEntity<?> update(@RequestBody Tarefa tarefa) {
        try {
            tarefa = service.update(tarefa);
            return new ResponseEntity<>(tarefa, HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/finalizadasfalse")
    public List<Tarefa> getAllUnfinished() {
        return service.getAllUnfinished();
    }

    @GetMapping("/finalizadastrue")
    public List<Tarefa> getAllFinished() {
        return service.getAllFinished();
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody Tarefa tarefa) {
        try {
            service.delete(tarefa);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/atrasadas")
    public List<Tarefa> getAllLate() {
        return service.getAllLate();
    }

    @GetMapping("/abertas")
    public ResponseEntity<List <Tarefa>> getByDateRange(@RequestBody FiltroDTO dto) {
        try {
            var lista = service.getByDateRange(dto.getDtInicio(), dto.getDtFinal());
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> finalizarTarefa(@PathVariable Long id){
        try {
            var excluded = service.finishTaskById(id);
            if(excluded){
                return new ResponseEntity<>("Tarefa Finalizada!", HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return null;
    }

}
