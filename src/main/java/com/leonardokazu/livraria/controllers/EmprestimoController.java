package com.leonardokazu.livraria.controllers;

import com.leonardokazu.livraria.entities.DTOS.EmprestimoDTORequest;
import com.leonardokazu.livraria.entities.DTOS.EmprestimoDTOResponse;
import com.leonardokazu.livraria.services.EmprestimoService;
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
@RequestMapping("/emprestimo")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @Operation(description = "Empresta um livro para um leitor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Novo emprestimo salvo"),
            @ApiResponse(responseCode = "400", description = "O livro ja foi emprestado"),
            @ApiResponse(responseCode = "404", description = "Nenhum registro foi encontrado para este id!")
    })
    @PostMapping
    public ResponseEntity<EmprestimoDTOResponse> emprestar(@RequestBody @Valid EmprestimoDTORequest emprestimoDTORequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(emprestimoService.emprestar(emprestimoDTORequest));
    }
    @Operation(description = "Devolve um livro através do id e fecha seu emprestimo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Livro devolvido com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Livro ou Leitor não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<String> devolver(@PathVariable Long id){
        emprestimoService.devolver(id);
        return ResponseEntity.status(HttpStatus.OK).body("Livro devolvido com sucesso!");
    }

    @Operation(description = "Busca todos emprestimos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Retorna todos os emprestimos"),
            @ApiResponse(responseCode = "404", description = "Nenhum emprestimo foi encontrado")
    })
    @GetMapping
    public ResponseEntity<List<EmprestimoDTOResponse>> lerTodos(){
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.lerTodos());
    }

    @Operation(description = "Busca emprestimo pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna o emprestimo"),
            @ApiResponse(responseCode = "404", description = "Nenhum registro foi encontrado para este id!")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EmprestimoDTOResponse> lerPorId(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.lerPorId(id));
    }
}
