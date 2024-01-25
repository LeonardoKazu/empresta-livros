package com.leonardokazu.livraria.services;

import com.leonardokazu.livraria.entities.DTOS.LeitorDTORequest;
import com.leonardokazu.livraria.entities.DTOS.LeitorDTOResponse;
import com.leonardokazu.livraria.entities.Leitor;
import com.leonardokazu.livraria.exceptions.ResourceBadRequestException;
import com.leonardokazu.livraria.exceptions.ResourceNotFoundException;
import com.leonardokazu.livraria.repositories.LeitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeitorService {

    @Autowired
    private LeitorRepository leitorRepository;

    public LeitorDTOResponse salvar(LeitorDTORequest leitorDTORequest){
        try {
            Leitor leitor = new Leitor();

            leitor.setNome(leitorDTORequest.nome());
            leitor.setEmail(leitorDTORequest.email());

            leitorRepository.save(leitor);
            LeitorDTOResponse response = new LeitorDTOResponse(leitor.getId(), leitor.getNome(), leitor.getEmail());
            return response;
        } catch (Exception e){
            throw new ResourceBadRequestException();
        }
    }

    public List<LeitorDTOResponse> lerTodos(){
        List<Leitor> leitores = leitorRepository.findAll();
        if (leitores.isEmpty()){
            throw new ResourceNotFoundException("Nenhum registro foi encontrado!");
        }
        List<LeitorDTOResponse> response = new ArrayList<>();
        for (Leitor x : leitores){
            response.add(new LeitorDTOResponse(x.getId(), x.getNome(), x.getEmail()));
        }
        return response;
    }

    public LeitorDTOResponse lerPorId(Long id){
        var leitor = leitorRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        return new LeitorDTOResponse(leitor.getId(), leitor.getNome(), leitor.getEmail());
    }

    public LeitorDTOResponse atualizarPorId(LeitorDTORequest leitorDTORequest, Long id){

        Leitor leitor = leitorRepository.findById(id).orElseThrow(ResourceNotFoundException::new);

        leitor.setNome(leitorDTORequest.nome());
        leitor.setEmail(leitorDTORequest.email());
        leitorRepository.save(leitor);

        return new LeitorDTOResponse(leitor.getId(), leitor.getNome(), leitor.getEmail());
    }
    public void deletar(Long id){
        leitorRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        leitorRepository.deleteById(id);
    }
}
