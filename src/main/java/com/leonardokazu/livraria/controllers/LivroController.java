package com.leonardokazu.livraria.controllers;

import com.leonardokazu.livraria.entities.DTOS.LivroDTORequest;
import com.leonardokazu.livraria.entities.Livro;
import com.leonardokazu.livraria.services.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @PostMapping()
    public ResponseEntity<Livro> salvar(@RequestBody LivroDTORequest livroDTORequest){

        return ResponseEntity.status(HttpStatus.CREATED).body(livroService.salvar(livroDTORequest));
    }
    @GetMapping
    public ResponseEntity<List<Livro>> lerTodos(){
        return ResponseEntity.status(HttpStatus.OK).body(livroService.lerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> lerPorId(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(livroService.lerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizarPorId(@RequestBody LivroDTORequest livroDTORequest, @PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(livroService.atualizar(livroDTORequest, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id){
        livroService.deletarPorId(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
