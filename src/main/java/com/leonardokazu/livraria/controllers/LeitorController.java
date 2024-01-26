package com.leonardokazu.livraria.controllers;

import com.leonardokazu.livraria.entities.DTOS.LeitorDTORequest;
import com.leonardokazu.livraria.entities.DTOS.LeitorDTOResponse;
import com.leonardokazu.livraria.services.LeitorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(description = "Salva um novo leitor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Novo leitor salvo"),
            @ApiResponse(responseCode = "400", description = "leitor não salvo [Bad-Request]")
    })
    @PostMapping
    public ResponseEntity<LeitorDTOResponse> salvar(@RequestBody @Valid LeitorDTORequest leitorDTORequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(leitorService.salvar(leitorDTORequest));
    }

    @Operation(description = "Busca todos leitores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna todos os leitores"),
            @ApiResponse(responseCode = "404", description = "Nenhum leitor foi encontrado!")
    })
    @GetMapping
    public ResponseEntity<List<LeitorDTOResponse>> lerTodos(){
        return ResponseEntity.status(HttpStatus.OK).body(leitorService.lerTodos());
    }
    @Operation(description = "Busca leitor pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna o leitor"),
            @ApiResponse(responseCode = "404", description = "Nenhum registro foi encontrado para este id!")
    })
    @GetMapping("/{id}")
    public ResponseEntity<LeitorDTOResponse> lerPorId(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(leitorService.lerPorId(id));
    }

    @Operation(description = "Atualiza o leitor buscado pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "leitor atualizado"),
            @ApiResponse(responseCode = "404", description = "Nenhum registro foi encontrado para este id!"),
            @ApiResponse(responseCode = "400", description = "Leitor não salvo [Bad Request]")
    })
    @PutMapping("/{id}")
    public ResponseEntity<LeitorDTOResponse> atualizarPorId(@RequestBody @Valid LeitorDTORequest leitorDTORequest, @PathVariable Long id){
         return ResponseEntity.status(HttpStatus.OK).body(leitorService.atualizarPorId(leitorDTORequest, id));
    }
    @Operation(description = "Deleta o leitor buscado pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Leitor deletado"),
            @ApiResponse(responseCode = "404", description = "Nenhum registro foi encontrado para este id!")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        leitorService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
