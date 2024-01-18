package com.leonardokazu.livraria.services;

import com.leonardokazu.livraria.repositories.LeitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeitorService {

    @Autowired
    private LeitorRepository leitorRepository;


}
