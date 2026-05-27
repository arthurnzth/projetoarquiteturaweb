package com.arquiteturaweb.estoque.consumers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.arquiteturaweb.estoque.events.CadastroProdutoEvent;
import com.arquiteturaweb.estoque.services.EstoqueService;

@Service
public class ProdutoConsumer {

    @Autowired
    private EstoqueService estoqueService;

    @KafkaListener(topics = "cadastro-produto-topic", groupId = "estoque-group")
    public void consumirCadastroProduto(CadastroProdutoEvent event) {
        estoqueService.criarEstoqueInicial(event.getProdutoId());
    }

}
