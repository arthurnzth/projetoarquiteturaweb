package com.arquiteturaweb.estoque.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arquiteturaweb.estoque.entities.Estoque;
import com.arquiteturaweb.estoque.entities.Produto;
import com.arquiteturaweb.estoque.entities.dto.estoque.EstoqueResponseDTO;
import com.arquiteturaweb.estoque.repositories.EstoqueRepository;
import com.arquiteturaweb.estoque.repositories.ProdutoRepository;
import com.arquiteturaweb.estoque.services.exceptions.ResourceNotFoundException;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<EstoqueResponseDTO> findAll() {

        List<Estoque> estoques = estoqueRepository.findAll();
        return estoques.stream().map(e -> EstoqueResponseDTO.converterEstoque(e)).collect(Collectors.toList());

    }

    public EstoqueResponseDTO findById(Long id) {

        Optional<Estoque> obj = estoqueRepository.findById(id);
        return EstoqueResponseDTO.converterEstoque(obj.orElseThrow(() -> new ResourceNotFoundException(id)));

    }

    public void criarEstoqueInicial(Long produtoId) {

        Estoque estoque = new Estoque();
        Produto produto = produtoRepository.findById(produtoId).orElseThrow(() -> new ResourceNotFoundException(produtoId));
        estoque.setProduto(produto);
        estoque.setQuantidade(0L);
        estoqueRepository.save(estoque);
        produto.setEstoque(estoque);
        produtoRepository.save(produto);

    }

}
