package com.arquiteturaweb.estoque.entities.dto.itemPedido;

import java.io.Serializable;

public class ItemPedidoRequestDTO implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private Integer quantidade;
    private Long produtoId;

    public ItemPedidoRequestDTO() {

    }

    public ItemPedidoRequestDTO(Integer quantidade, Long produtoId) {
        this.quantidade = quantidade;
        this.produtoId = produtoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }
    
}
