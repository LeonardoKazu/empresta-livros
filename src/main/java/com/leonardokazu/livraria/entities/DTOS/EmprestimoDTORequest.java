package com.leonardokazu.livraria.entities.DTOS;


import jakarta.validation.constraints.NotNull;

public record EmprestimoDTORequest(
        @NotNull
        Long leitorId,
        @NotNull
        Long livroId) {
}
