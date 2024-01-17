package com.leonardokazu.livraria.services;

import com.leonardokazu.livraria.entities.DTOS.LivroDTO;
import com.leonardokazu.livraria.entities.Livro;
import com.leonardokazu.livraria.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public Livro salvar(LivroDTO livroDTO) {
        Livro livro = new Livro();

        livro.setNome(livroDTO.nome());
        livro.setAutor(livroDTO.autor());
        livro.setTotalPaginas(livroDTO.totalPaginas());

        return livroRepository.save(livro);
    }

    public List<Livro> lerTodos(){
        return livroRepository.findAll();
    }

    public Livro lerPorId(Long id){
        return livroRepository.findById(id).get();
    }

    public Livro atualizar(LivroDTO livroDTO, Long id){
        Livro livro = livroRepository.findById(id).get();

        livro.setNome(livroDTO.nome());
        livro.setAutor(livroDTO.autor());
        livro.setTotalPaginas(livroDTO.totalPaginas());

        return livroRepository.save(livro);
    }
    public void deletarPorId(Long id){
        livroRepository.deleteById(id);
    }
}
