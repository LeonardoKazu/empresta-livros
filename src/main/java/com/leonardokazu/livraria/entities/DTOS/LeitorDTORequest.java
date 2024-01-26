package com.leonardokazu.livraria.entities.DTOS;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record LeitorDTORequest(
        @NotEmpty
        String nome,
        @NotEmpty
        @Email
        String email) {
}
