package com.leonardokazu.livraria.repositories;

import com.leonardokazu.livraria.entities.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprestimoRespository extends JpaRepository<Emprestimo, Long> {
}
