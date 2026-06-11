package com.arquiteturaweb.estoque.entities.dto.itemVenda;

import java.io.Serializable;

public class ItemVendaRequestDTO implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private Integer quantidade;
    private Long produtoId;

    public ItemVendaRequestDTO() {
        
    }

    public ItemVendaRequestDTO(Integer quantidade, Long produtoId) {
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
