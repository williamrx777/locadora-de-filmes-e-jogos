package com.will.locadora.Controller;

import com.will.locadora.DTO.FilmesMinRecord;
import com.will.locadora.DTO.FilmesRecord;
import com.will.locadora.Entity.Filmes;
import com.will.locadora.Repository.FilmesRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/filmes")
public class FilmesController {

    @Autowired
    private FilmesRepository filmesRepository;

    @GetMapping
    public ResponseEntity<List<FilmesMinRecord>> listar(){
        var listar = filmesRepository.findAll().stream().map(FilmesMinRecord::new).toList();
        return ResponseEntity.ok(listar);
    }
    @PostMapping
    @Transactional
    public ResponseEntity<Filmes> postarFilme(@RequestBody @Valid FilmesRecord filmesRecord){
       var filmes = new Filmes();
       BeanUtils.copyProperties(filmesRecord, filmes);
       return ResponseEntity.status(HttpStatus.CREATED).body(filmesRepository.save(filmes));
    }
    @GetMapping("/{codigo}")
    public ResponseEntity<Object> pegaFilme(@PathVariable Long codigo) {
        Optional<Filmes> filmes = filmesRepository.findById(codigo);
        if(filmes.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Falha ao busca filme, veja o codigo se o filme realmente existe");
        }
        return ResponseEntity.status(HttpStatus.OK).body(filmes);
    }

    @PutMapping("/{codigo}")
    @Transactional
    public ResponseEntity<Object> atualizarFilme(@RequestBody @Valid FilmesRecord filmesRecord, @PathVariable Long codigo){
        Optional<Filmes> filmes = filmesRepository.findById(codigo);
        if(filmes.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Falha na atualização de filme reveja o codigo");
        }
        var filme = filmes.get();
        BeanUtils.copyProperties(filmesRecord, filme);
        return ResponseEntity.status(HttpStatus.OK).body(filme);
    }
    @DeleteMapping("/{codigo}")
    public ResponseEntity<Object> deletar(@PathVariable Long codigo){
        Optional<Filmes> filmes = filmesRepository.findById(codigo);
        if(filmes.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Falha ao excluir filme reveja o codigo");
        }
        filmesRepository.delete(filmes.get());
        return ResponseEntity.status(HttpStatus.OK).body("Excluido com sucesso");
    }
}
