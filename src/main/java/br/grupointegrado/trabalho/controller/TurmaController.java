package br.grupointegrado.trabalho.controller;

import br.grupointegrado.trabalho.model.Curso;
import br.grupointegrado.trabalho.model.Turma;
import br.grupointegrado.trabalho.repository.CursoRepository;
import br.grupointegrado.trabalho.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turmas")
public class TurmaController {

    @Autowired
    TurmaRepository turmaRepository;

    @Autowired
    CursoRepository cursoRepository;

    @PostMapping("/{id}/add-turma")
    public ResponseEntity<Curso> addTurma(@PathVariable Integer id,
                                          @RequestBody Turma turma){
        Curso curso = this.cursoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));

        turma.setCurso(curso);
        this.turmaRepository.save(turma);

        return ResponseEntity.ok(curso);
    }

    @GetMapping
    public ResponseEntity<List<Turma>> findAll(){
        return ResponseEntity.ok(this.turmaRepository.findAll());
    }

}