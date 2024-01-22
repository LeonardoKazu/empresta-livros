package com.leonardokazu.livraria.entities.DTOS;

import com.leonardokazu.livraria.entities.Emprestimo;

public record EmprestimoDTOResponse(Long idLeitor, String nomeLeitor, String emailLeitor, Emprestimo emprestimo) {
}
