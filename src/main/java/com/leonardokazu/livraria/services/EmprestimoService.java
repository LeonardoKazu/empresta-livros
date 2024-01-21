package com.leonardokazu.livraria.services;

import com.leonardokazu.livraria.entities.DTOS.EmprestimoDTO;
import com.leonardokazu.livraria.entities.Emprestimo;
import com.leonardokazu.livraria.entities.Leitor;
import com.leonardokazu.livraria.entities.Livro;
import com.leonardokazu.livraria.repositories.EmprestimoRespository;
import com.leonardokazu.livraria.repositories.LeitorRepository;
import com.leonardokazu.livraria.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmprestimoService {
    @Autowired
    private EmprestimoRespository emprestimoRespository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private LeitorRepository leitorRepository;

    public List<Emprestimo> lerTodos(){
        return emprestimoRespository.findAll();
    }
    public void emprestar(EmprestimoDTO emprestimoDTO){
        Livro livro = livroRepository.findById(emprestimoDTO.livroId()).get();
        if (livro.isDisponivel() == false){
            throw new RuntimeException("O livro já foi emprestado");
        }
        Leitor leitor = leitorRepository.findById(emprestimoDTO.leitorId()).get();
        livro.setDisponivel(false);
        Emprestimo emprestimo = new Emprestimo(livro, leitor);
        leitor.addEmprestimo(emprestimo);



        emprestimoRespository.save(emprestimo);
        leitorRepository.save(leitor);
        livroRepository.save(livro);
    }
    public void devolver(Long id){
        Emprestimo emprestimo = emprestimoRespository.findById(id).get();

        Livro livro = livroRepository.findById(emprestimo.getLivro().getId()).get();
        Leitor leitor = leitorRepository.findById(emprestimo.getLeitor().getId()).get();

        livro.setDisponivel(true);
        leitor.devolver(id);
        livroRepository.save(livro);
        leitorRepository.save(leitor);

    }
}
