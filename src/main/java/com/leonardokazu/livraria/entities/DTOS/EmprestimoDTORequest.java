package com.leonardokazu.livraria.entities.DTOS;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EmprestimoDTORequest(
        @NotNull(message = "leitorId não pode ser nulo!")
        @NotBlank(message = "leitorId não pode ser vazio!")
        Long leitorId,
        @NotNull(message = "livroId não pode ser nulo!")
        @NotBlank(message = "livroId não pode ser vazio!")
        Long livroId) {
}
