package com.leonardokazu.livraria.services;

import com.leonardokazu.livraria.entities.DTOS.LivroDTORequest;
import com.leonardokazu.livraria.entities.Livro;
import com.leonardokazu.livraria.exceptions.ResourceBadRequestException;
import com.leonardokazu.livraria.exceptions.ResourceNotFoundException;
import com.leonardokazu.livraria.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public Livro salvar(LivroDTORequest livroDTORequest) {
        try{
            Livro livro = new Livro();

            livro.setNome(livroDTORequest.nome());
            livro.setAutor(livroDTORequest.autor());
            livro.setTotalPaginas(livroDTORequest.totalPaginas());

            return livroRepository.save(livro);
        } catch (Exception e){
            throw new ResourceBadRequestException();
        }
    }

    public List<Livro> lerTodos(){
        var listaDeLivros = livroRepository.findAll();
        if (listaDeLivros.isEmpty()){
            throw new ResourceNotFoundException("Nenhum livro foi encontrado!");
        }
        return listaDeLivros;
    }

    public Livro lerPorId(Long id){
        return livroRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public Livro atualizar(LivroDTORequest livroDTORequest, Long id){
        Livro livro = livroRepository.findById(id).orElseThrow(ResourceNotFoundException::new);

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
