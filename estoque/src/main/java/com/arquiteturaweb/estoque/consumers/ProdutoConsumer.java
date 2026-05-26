package com.arquiteturaweb.estoque.consumers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.arquiteturaweb.estoque.entities.Produto;
import com.arquiteturaweb.estoque.events.CadastroProdutoEvent;
import com.arquiteturaweb.estoque.repositories.ProdutoRepository;
import com.arquiteturaweb.estoque.services.EstoqueService;
import com.arquiteturaweb.estoque.services.exceptions.ResourceNotFoundException;

@Service
public class ProdutoConsumer {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private EstoqueService estoqueService;

    @KafkaListener(topics = "cadastro-produto-topic", groupId = "estoque-group")
    public void consumirCadastroProduto(CadastroProdutoEvent event) {
        Produto produto = produtoRepository.findById(event.getProdutoId()).orElseThrow(() -> new ResourceNotFoundException(event.getProdutoId()));
        estoqueService.criarEstoqueInicial(produto);
    }

}
