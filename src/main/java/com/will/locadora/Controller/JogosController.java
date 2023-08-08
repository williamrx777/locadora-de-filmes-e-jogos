package com.will.locadora.Controller;

import com.will.locadora.DTO.JogosMinRecord;
import com.will.locadora.DTO.JogosRecord;
import com.will.locadora.Entity.Jogos;
import com.will.locadora.Repository.JogosRepository;
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
@RequestMapping("/jogos")
public class JogosController {

    @Autowired
    private JogosRepository jogosRepository;
    @GetMapping
    public List<JogosMinRecord> listar(){
        var jogos = jogosRepository.findAll().stream().map(JogosMinRecord::new).toList();
        return jogos;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Jogos> postarJogo(@RequestBody @Valid JogosRecord jogosRecord){
        var jogos = new Jogos();
        BeanUtils.copyProperties(jogosRecord, jogos);
        return ResponseEntity.status(HttpStatus.CREATED).body(jogosRepository.save(jogos));
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Object> pegaJogo(@PathVariable Long codigo){
        Optional<Jogos> jogos = jogosRepository.findById(codigo);
        if(jogos.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Falha ao busca filme, veja o codigo se o jogo realmente existe");
        }
        return ResponseEntity.status(HttpStatus.OK).body(jogos);
    }

    @PutMapping("/{codigo}")
    @Transactional
    public ResponseEntity<Object> atualizarJogo(@RequestBody @Valid JogosRecord jogosRecord, @PathVariable Long codigo){
        Optional<Jogos> jogos = jogosRepository.findById(codigo);
        if(jogos.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Falha na atualização de jogo reveja o codigo");
        }
        var jogo = jogos.get();
        BeanUtils.copyProperties(jogosRecord, jogo);
        return ResponseEntity.status(HttpStatus.OK).body(jogosRepository.save(jogo));
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Object> deletarJogo(@PathVariable Long codigo){
        Optional<Jogos> jogos = jogosRepository.findById(codigo);
        if(jogos.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Jogo invalido reveja o codigo");
        }
        jogosRepository.delete(jogos.get());
        return ResponseEntity.status(HttpStatus.OK).body("Jogo deletado com sucesso");
    }
}
