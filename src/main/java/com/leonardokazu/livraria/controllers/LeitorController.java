package com.leonardokazu.livraria.controllers;

import com.leonardokazu.livraria.services.LeitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/leitor")
public class LeitorController {

    @Autowired
    private LeitorService leitorService;

    @GetMapping
    public String teste(){
        return "Hello world";
    }
}
