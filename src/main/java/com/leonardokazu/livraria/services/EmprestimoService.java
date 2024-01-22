package com.leonardokazu.livraria.services;

import com.leonardokazu.livraria.entities.DTOS.EmprestimoDTORequest;
import com.leonardokazu.livraria.entities.DTOS.EmprestimoDTOResponse;
import com.leonardokazu.livraria.entities.Emprestimo;
import com.leonardokazu.livraria.entities.Leitor;
import com.leonardokazu.livraria.entities.Livro;
import com.leonardokazu.livraria.repositories.EmprestimoRespository;
import com.leonardokazu.livraria.repositories.LeitorRepository;
import com.leonardokazu.livraria.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmprestimoService {
    @Autowired
    private EmprestimoRespository emprestimoRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private LeitorRepository leitorRepository;

    public List<EmprestimoDTOResponse> lerTodos(){
        List<Emprestimo> emprestimos =  emprestimoRepository.findAll();
        List<EmprestimoDTOResponse> responses = new ArrayList<>();
        for (Emprestimo x : emprestimos){
            responses.add(new EmprestimoDTOResponse(x.getId(), x.getLeitor().getNome(), x.getLeitor().getEmail(), x));
        }
        return responses;
    }

    public EmprestimoDTOResponse lerPorId(Long id){
        var emprestimo = emprestimoRepository.findById(id).orElseThrow(() -> new RuntimeException("Emprestimo não foi encontrado"));
        var response = new EmprestimoDTOResponse(emprestimo.getLeitor().getId(), emprestimo.getLeitor().getNome(),
                emprestimo.getLeitor().getEmail(), emprestimo);
        return response;
    }

    public EmprestimoDTOResponse emprestar(EmprestimoDTORequest emprestimoDTORequest){
        Livro livro = livroRepository.findById(emprestimoDTORequest.livroId()).get();
        if (!livro.isDisponivel()){
            throw new RuntimeException("O livro já foi emprestado");
        }
        Leitor leitor = leitorRepository.findById(emprestimoDTORequest.leitorId()).get();
        livro.setDisponivel(false);
        Emprestimo emprestimo = new Emprestimo(livro, leitor);
        leitor.addEmprestimo(emprestimo);

        emprestimoRepository.save(emprestimo);
        livro.setEmprestimoId(emprestimo.getId());
        leitorRepository.save(leitor);
        livroRepository.save(livro);
        return new EmprestimoDTOResponse(leitor.getId(), leitor.getNome(), leitor.getEmail(), emprestimo);
    }
    public void devolver(Long id){
        Emprestimo emprestimo = emprestimoRepository.findById(id).get();

        Livro livro = livroRepository.findById(emprestimo.getLivro().getId()).get();
        Leitor leitor = leitorRepository.findById(emprestimo.getLeitor().getId()).get();

        livro.setDisponivel(true);
        emprestimo.devolver();
        emprestimoRepository.deleteById(emprestimo.getId());
        livroRepository.save(livro);
        leitorRepository.save(leitor);
    }
}
