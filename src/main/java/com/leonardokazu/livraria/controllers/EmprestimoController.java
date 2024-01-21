package com.leonardokazu.livraria.controllers;

import com.leonardokazu.livraria.entities.DTOS.EmprestimoDTO;
import com.leonardokazu.livraria.entities.Emprestimo;
import com.leonardokazu.livraria.services.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @PostMapping
    public ResponseEntity<String> emprestar(@RequestBody EmprestimoDTO emprestimoDTO){
        emprestimoService.emprestar(emprestimoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Emprestimo realizado com sucesso!");
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> devolver(@PathVariable Long id){
        emprestimoService.devolver(id);
        return ResponseEntity.status(HttpStatus.OK).body("Livro devolvido com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<Emprestimo>> lerTodos(){
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.lerTodos());
    }

}
