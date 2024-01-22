package com.leonardokazu.livraria.services;

import com.leonardokazu.livraria.entities.DTOS.LivroDTORequest;
import com.leonardokazu.livraria.entities.Livro;
import com.leonardokazu.livraria.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public Livro salvar(LivroDTORequest livroDTORequest) {
        Livro livro = new Livro();

        livro.setNome(livroDTORequest.nome());
        livro.setAutor(livroDTORequest.autor());
        livro.setTotalPaginas(livroDTORequest.totalPaginas());

        return livroRepository.save(livro);
    }

    public List<Livro> lerTodos(){
        return livroRepository.findAll();
    }

    public Livro lerPorId(Long id){
        return livroRepository.findById(id).orElseThrow(() -> new RuntimeException("Livro não foi encontrado"));
    }

    public Livro atualizar(LivroDTORequest livroDTORequest, Long id){
        Livro livro = livroRepository.findById(id).orElseThrow(() -> new RuntimeException("Livro não foi encontrado"));

        livro.setNome(livroDTORequest.nome());
        livro.setAutor(livroDTORequest.autor());
        livro.setTotalPaginas(livroDTORequest.totalPaginas());

        return livroRepository.save(livro);
    }
    public void deletarPorId(Long id){
        lerPorId(id);
        livroRepository.deleteById(id);
    }
}
