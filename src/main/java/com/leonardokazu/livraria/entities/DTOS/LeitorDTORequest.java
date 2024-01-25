package com.leonardokazu.livraria.entities.DTOS;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LeitorDTORequest(
        @NotNull(message = "nome não pode ser nulo!")
        @NotBlank(message = "nome não pode ser vazio!")
        String nome,
        @NotNull(message = "email não pode ser nulo!")
        @NotBlank(message = "email não pode ser vazio!")
        String email) {
}
