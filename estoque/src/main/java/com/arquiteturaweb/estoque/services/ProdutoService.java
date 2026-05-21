package com.arquiteturaweb.estoque.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arquiteturaweb.estoque.entities.Produto;
import com.arquiteturaweb.estoque.repositories.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repositorio;

    public List<Produto> findAll() {
        return repositorio.findAll();
    }

    public Produto findById(Long id) {
        Optional<Produto> obj = repositorio.findById(id);
        return obj.get();
    }

}
