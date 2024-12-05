package br.grupointegrado.trabalho.controller;

import br.grupointegrado.trabalho.dto.TurmaRequestDTO;
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

    @GetMapping
    public ResponseEntity<List<Turma>> findAll(){
        return ResponseEntity.ok(this.turmaRepository.findAll());
    }

    @DeleteMapping("/{id}/deletar-turma")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        Turma turma = this.turmaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrado"));

        this.turmaRepository.delete(turma);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/add-turma")
    public ResponseEntity<Curso> addTurma(@RequestBody TurmaRequestDTO dto){
        Curso curso = this.cursoRepository.findById(dto.cursoId())
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));

        Turma turma = new Turma();
        turma.setCurso(curso);
        turma.setAno(dto.ano());
        turma.setSemestre(dto.semestre());
        this.turmaRepository.save(turma);

        return ResponseEntity.ok(curso);
    }

    @PutMapping("/{id}/atualuzar-turma")
    public ResponseEntity<Turma> update(@PathVariable Integer id,
                                        @RequestBody TurmaRequestDTO dto) {
        Turma turma = this.turmaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrado"));
        Curso curso = this.cursoRepository.findById(dto.cursoId())
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));

        turma.setCurso(curso);
        turma.setAno(dto.ano());
        turma.setSemestre(dto.semestre());

        return ResponseEntity.ok(this.turmaRepository.save(turma));
    }

}