package com.leonardokazu.livraria.services;

import com.leonardokazu.livraria.entities.DTOS.EmprestimoDTORequest;
import com.leonardokazu.livraria.entities.DTOS.EmprestimoDTOResponse;
import com.leonardokazu.livraria.entities.Emprestimo;
import com.leonardokazu.livraria.entities.Leitor;
import com.leonardokazu.livraria.entities.Livro;
import com.leonardokazu.livraria.exceptions.ResourceNotFoundException;
import com.leonardokazu.livraria.repositories.EmprestimoRespository;
import com.leonardokazu.livraria.repositories.LeitorRepository;
import com.leonardokazu.livraria.repositories.LivroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class EmprestimoService {
    @Autowired
    private EmprestimoRespository emprestimoRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private LeitorRepository leitorRepository;

    public List<EmprestimoDTOResponse> lerTodos(){
        try {
            List<Emprestimo> emprestimos = emprestimoRepository.findAll();
            List<EmprestimoDTOResponse> responses = new ArrayList<>();
            for (Emprestimo x : emprestimos) {
                responses.add(new EmprestimoDTOResponse(x.getLeitor().getId(), x.getLeitor().getNome(), x.getLeitor().getEmail(), x));
            }
            return responses;
        }catch (Exception e){
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    public EmprestimoDTOResponse lerPorId(Long id){
        var emprestimo = emprestimoRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        var response = new EmprestimoDTOResponse(emprestimo.getLeitor().getId(), emprestimo.getLeitor().getNome(),
                emprestimo.getLeitor().getEmail(), emprestimo);
        return response;
    }

    public EmprestimoDTOResponse emprestar(EmprestimoDTORequest emprestimoDTORequest){
        try {
            Livro livro = livroRepository.findById(emprestimoDTORequest.livroId()).get();
            if (!livro.isDisponivel()) {
                throw new Exception("O livro já foi emprestado");
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
        }catch (Exception e){
            throw new ResourceNotFoundException(e.getMessage());
        }
    }
    public void devolver(Long id){
        try {
            Emprestimo emprestimo = emprestimoRepository.findById(id).get();

            Livro livro = livroRepository.findById(emprestimo.getLivro().getId()).get();
            Leitor leitor = leitorRepository.findById(emprestimo.getLeitor().getId()).get();
            emprestimo.devolver();
            leitor.removeEmprestimos(emprestimo);
            livro.setEmprestimoId(null);
            livro.setDisponivel(true);
            livroRepository.save(livro);
            leitorRepository.save(leitor);
            emprestimoRepository.deleteById(emprestimo.getId());
        } catch (Exception e){
            throw new ResourceNotFoundException(e.getMessage());
        }
    }
}
