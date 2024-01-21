package com.leonardokazu.livraria.services;

import com.leonardokazu.livraria.entities.DTOS.LeitorDTO;
import com.leonardokazu.livraria.entities.Leitor;
import com.leonardokazu.livraria.repositories.LeitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeitorService {

    @Autowired
    private LeitorRepository leitorRepository;

    public Leitor salvar(LeitorDTO leitorDTO){
        Leitor leitor = new Leitor();

        leitor.setNome(leitorDTO.nome());
        leitor.setEmail(leitorDTO.email());
        leitor.setCpf(leitorDTO.cpf());

        return leitorRepository.save(leitor);
    }

    public List<Leitor> lerTodos(){
        return leitorRepository.findAll();
    }

    public Leitor lerPorId(Long id){
        return leitorRepository.findById(id).get();
    }

    public Leitor atualizarPorId(LeitorDTO leitorDTO, Long id){
        Leitor leitor = lerPorId(id);

        leitor.setNome(leitorDTO.nome());
        leitor.setEmail(leitorDTO.email());
        leitor.setCpf(leitorDTO.cpf());

        return leitorRepository.save(leitor);
    }
    public void deletar(Long id){
        leitorRepository.deleteById(id);
    }
}
