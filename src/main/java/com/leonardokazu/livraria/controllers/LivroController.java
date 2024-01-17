package com.leonardokazu.livraria.controllers;

import com.leonardokazu.livraria.entities.DTOS.LivroDTO;
import com.leonardokazu.livraria.entities.Livro;
import com.leonardokazu.livraria.services.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @PostMapping()
    public ResponseEntity<Livro> salvar(@RequestBody LivroDTO livroDTO){

        return ResponseEntity.status(HttpStatus.CREATED).body(livroService.salvar(livroDTO));
    }
}
