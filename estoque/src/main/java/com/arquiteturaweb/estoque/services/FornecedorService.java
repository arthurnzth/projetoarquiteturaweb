package com.arquiteturaweb.estoque.services;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.arquiteturaweb.estoque.entities.Fornecedor;
import com.arquiteturaweb.estoque.repositories.FornecedorRepository;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepositorio;

    public FornecedorService(FornecedorRepository repository) {
        this.fornecedorRepositorio = repository;
    }

    //FUNCTIONS GET
    public List<Fornecedor> findAll() {
        return fornecedorRepositorio.findAll();
    }

    public Fornecedor findById(Long id) {
        Optional<Fornecedor> obj = fornecedorRepositorio.findById(id);
        return obj.get();
    }

    //FUNCTIONS POST
    public Fornecedor save(Fornecedor fornecedor) {
        if (fornecedor.getNomeFornecedor() == null || fornecedor.getNomeFornecedor().isBlank()) {
            throw new IllegalArgumentException("Nome do fornecedor é obrigatório");
        }

        if (fornecedor.getEnderecoFornecedor() == null || fornecedor.getEnderecoFornecedor().isBlank()) {
            throw new IllegalArgumentException("Endereço inválido");
        }

        if (fornecedor.getTelefoneFornecedor() == null || fornecedor.getTelefoneFornecedor().isBlank()) {
            throw new IllegalArgumentException("Telefone inválido");
        }

        return fornecedorRepositorio.save(fornecedor);
    }

    //FUNCTIONS PUT
    public Fornecedor update(Long id, Fornecedor dados){

        Fornecedor fornecedor = fornecedorRepositorio.findById(id).orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));

        // Atualiza os campos
        fornecedor.setNomeFornecedor(dados.getNomeFornecedor());
        fornecedor.setEnderecoFornecedor(dados.getEnderecoFornecedor());
        fornecedor.setTelefoneFornecedor(dados.getTelefoneFornecedor());

        return fornecedorRepositorio.save(fornecedor);
    }

    //FUNCTIONS DELETE
    public void delete(Long id) {
        Fornecedor fornecedor = fornecedorRepositorio.findById(id).orElse(null);
        if(fornecedor == null) {
            throw new RuntimeException("Fornecedor não encontrado");
        }
        fornecedorRepositorio.delete(fornecedor);
    }

}
