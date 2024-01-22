package com.leonardokazu.livraria.controllers;

import com.leonardokazu.livraria.entities.DTOS.EmprestimoDTORequest;
import com.leonardokazu.livraria.entities.DTOS.EmprestimoDTOResponse;
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
    public ResponseEntity<EmprestimoDTOResponse> emprestar(@RequestBody EmprestimoDTORequest emprestimoDTORequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(emprestimoService.emprestar(emprestimoDTORequest));
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> devolver(@PathVariable Long id){
        emprestimoService.devolver(id);
        return ResponseEntity.status(HttpStatus.OK).body("Livro devolvido com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<EmprestimoDTOResponse>> lerTodos(){
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.lerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmprestimoDTOResponse> lerPorId(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.lerPorId(id));
    }

}
