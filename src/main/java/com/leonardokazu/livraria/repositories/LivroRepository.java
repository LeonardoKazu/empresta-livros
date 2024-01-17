package com.leonardokazu.livraria.repositories;

import com.leonardokazu.livraria.entities.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}
