package com.leonardokazu.livraria.controllers;

import com.leonardokazu.livraria.entities.DTOS.LeitorDTO;
import com.leonardokazu.livraria.entities.Leitor;
import com.leonardokazu.livraria.services.LeitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leitor")
public class LeitorController {

    @Autowired
    private LeitorService leitorService;

    @PostMapping
    public ResponseEntity<Leitor> salvar(@RequestBody LeitorDTO leitorDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(leitorService.salvar(leitorDTO));
    }

    @GetMapping
    public ResponseEntity<List<Leitor>> lerTodos(){
        return ResponseEntity.status(HttpStatus.OK).body(leitorService.lerTodos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Leitor> lerPorId(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(leitorService.lerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Leitor> atualizarPorId(@RequestBody LeitorDTO leitorDTO, @PathVariable Long id){
         return ResponseEntity.status(HttpStatus.OK).body(leitorService.atualizarPorId(leitorDTO, id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        leitorService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
