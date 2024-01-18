package com.leonardokazu.livraria.repositories;

import com.leonardokazu.livraria.entities.Leitor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeitorRepository extends JpaRepository<Leitor, Long> {
}
