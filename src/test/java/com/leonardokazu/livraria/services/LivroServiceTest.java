package com.leonardokazu.livraria.services;

import com.leonardokazu.livraria.entities.DTOS.LivroDTORequest;
import com.leonardokazu.livraria.entities.Livro;
import com.leonardokazu.livraria.exceptions.ResourceNotFoundException;
import com.leonardokazu.livraria.repositories.LivroRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class LivroServiceTest {

    private Livro livro;

    @InjectMocks
    private LivroService livroService;

    @Mock
    private LivroRepository livroRepository;

    @BeforeEach
    public void setup(){
        livro = new Livro(1L,"PedroMalazartes", "autorTeste", 60, true, null);
    }

    @Test
    void lerPorId_ReturnLivro_WhenLivroIsFoundById() {
        Mockito.when(livroRepository.findById(1L)).thenReturn(Optional.ofNullable(livro));
        Assertions.assertEquals(livro, livroService.lerPorId(1L));
    }

    @Test
    void lerPorId_ThrowsResourceNotFoundException_WhenLivroIsNotFoundById() {
//        Mockito.when(livroRepository.findById(1L)).thenReturn(Optional.ofNullable(livro));
        Assertions.assertThrows(ResourceNotFoundException.class, () -> livroService.lerPorId(1L));
    }

    @Test
    void lerTodos_ReturnListWithLivro_WhenListOfLivroIsFound(){
        Mockito.when(livroRepository.findAll()).thenReturn(Collections.singletonList(livro));
        Assertions.assertEquals(1, livroService.lerTodos().size());
    }

    @Test
    void lerTodos_ThrowsResourceNotFoundException_WhenListOfLivroIsNotFound(){
//        Mockito.when(livroRepository.findAll()).thenReturn(Collections.singletonList(livro));
        Assertions.assertThrows(ResourceNotFoundException.class, () -> livroService.lerTodos());
    }

    @Test
    void deletarPorId_ShouldCallDeleteById_WhenIdIsNotNull(){
        Mockito.when(livroRepository.findById(1L)).thenReturn(Optional.ofNullable(livro));

        livroService.deletarPorId(1L);
        Mockito.verify(livroRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    void deletarPorId_ThrowsResourceNotFoundException_WhenLivroIsNotFoundById(){
        Assertions.assertThrows(ResourceNotFoundException.class, () -> livroService.deletarPorId(1L));
    }

    @Test
    void salvar_ReturnLivro_WhenLivroDTORequestIsPassed(){

//        LivroDTORequest livroDTORequest = new LivroDTORequest(livro.getNome(), livro.getAutor(), livro.getTotalPaginas());
//        livroService.salvar(livroDTORequest);
//
//        Mockito.verify(livroRepository, Mockito.times(1)).save(any(Livro.class));
        Mockito.when(livroRepository.save(any(Livro.class))).thenReturn(livro);
        Livro livroResponse = livroService.salvar(new LivroDTORequest(livro.getNome(), livro.getAutor(), livro.getTotalPaginas()));

        Assertions.assertNotNull(livroResponse);
    }

}