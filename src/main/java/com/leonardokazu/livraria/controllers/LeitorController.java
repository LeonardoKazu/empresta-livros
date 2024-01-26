package com.leonardokazu.livraria.controllers;

import com.leonardokazu.livraria.entities.DTOS.LeitorDTORequest;
import com.leonardokazu.livraria.entities.DTOS.LeitorDTOResponse;
import com.leonardokazu.livraria.services.LeitorService;
import jakarta.validation.Valid;
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
    public ResponseEntity<LeitorDTOResponse> salvar(@RequestBody @Valid LeitorDTORequest leitorDTORequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(leitorService.salvar(leitorDTORequest));
    }

    @GetMapping
    public ResponseEntity<List<LeitorDTOResponse>> lerTodos(){
        return ResponseEntity.status(HttpStatus.OK).body(leitorService.lerTodos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<LeitorDTOResponse> lerPorId(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(leitorService.lerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeitorDTOResponse> atualizarPorId(@RequestBody @Valid LeitorDTORequest leitorDTORequest, @PathVariable Long id){
         return ResponseEntity.status(HttpStatus.OK).body(leitorService.atualizarPorId(leitorDTORequest, id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        leitorService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
