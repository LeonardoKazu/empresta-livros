package com.leonardokazu.livraria.controllers;

import com.leonardokazu.livraria.entities.DTOS.LivroDTORequest;
import com.leonardokazu.livraria.entities.Livro;
import com.leonardokazu.livraria.services.LivroService;
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
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @Operation(description = "Salva um novo livro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Novo livro salvo"),
            @ApiResponse(responseCode = "400", description = "livro não salvo [Bad-Request]")
    })
    @PostMapping()
    public ResponseEntity<Livro> salvar(@RequestBody @Valid LivroDTORequest livroDTORequest) {

        return ResponseEntity.status(HttpStatus.CREATED).body(livroService.salvar(livroDTORequest));
    }

    @Operation(description = "Busca todos os livros")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna todos livros"),
            @ApiResponse(responseCode = "404", description = "Nenhum livro foi encontrado!")
    })
    @GetMapping
    public ResponseEntity<List<Livro>> lerTodos() {
        return ResponseEntity.status(HttpStatus.OK).body(livroService.lerTodos());
    }

    @Operation(description = "Busca livro pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna o livro"),
            @ApiResponse(responseCode = "404", description = "Nenhum registro foi encontrado para este id!")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Livro> lerPorId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(livroService.lerPorId(id));
    }

    @Operation(description = "Atualiza o livro buscado pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro atualizado"),
            @ApiResponse(responseCode = "404", description = "Nenhum registro foi encontrado para este id!"),
            @ApiResponse(responseCode = "400", description = "Livro não salvo [Bad-Request]")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizarPorId(@RequestBody @Valid LivroDTORequest livroDTORequest, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(livroService.atualizar(livroDTORequest, id));
    }

    @Operation(description = "Deleta o livro buscado pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Livro deletado"),
            @ApiResponse(responseCode = "404", description = "Nenhum registro foi encontrado para este id!")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        livroService.deletarPorId(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
