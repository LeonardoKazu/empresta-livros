package com.leonardokazu.livraria.entities.DTOS;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public record LivroDTORequest(
        @NotEmpty
        String nome,
        @NotEmpty
        String autor,
        @NotNull
        Integer totalPaginas) {
}
