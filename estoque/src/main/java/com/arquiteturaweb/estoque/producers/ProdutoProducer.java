package com.arquiteturaweb.estoque.producers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.arquiteturaweb.estoque.events.CadastroProdutoEvent;

@Component
public class ProdutoProducer {

    @Autowired
    private final KafkaTemplate<String, CadastroProdutoEvent> kafkaTemplate;

    public ProdutoProducer(KafkaTemplate<String, CadastroProdutoEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void enviarCadastroProdutoEvent(CadastroProdutoEvent event) {
        kafkaTemplate.send("cadastro-produto-topic", event);
    }

}
