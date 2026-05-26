package com.arquiteturaweb.estoque.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arquiteturaweb.estoque.entities.Estoque;
import com.arquiteturaweb.estoque.entities.Produto;
import com.arquiteturaweb.estoque.repositories.EstoqueRepository;
import com.arquiteturaweb.estoque.services.exceptions.ResourceNotFoundException;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;

    public List<Estoque> findAll() {
        return estoqueRepository.findAll();
    }

    public Estoque findById(Long id) {
        Optional<Estoque> obj = estoqueRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public void criarEstoqueInicial(Produto produto) {
        Estoque estoque = new Estoque(null, produto);
        estoqueRepository.save(estoque);
    }

}
