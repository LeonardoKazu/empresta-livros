package com.leonardokazu.livraria.entities.DTOS;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LivroDTORequest(
        @NotNull(message = "nome não pode ser nulo!")
        @NotBlank(message = "nome não pode ser vazio!")
        String nome,
        @NotNull(message = "autor não pode ser nulo!")
        @NotBlank(message = "autor não pode ser vazio!")
        String autor,
        @NotNull(message = "totalPaginas não pode ser nulo!")
        @NotBlank(message = "totalPaginas não pode ser vazio!")
        Integer totalPaginas) {
}
