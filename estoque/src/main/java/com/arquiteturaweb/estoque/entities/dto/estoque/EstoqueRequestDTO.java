package com.arquiteturaweb.estoque.entities.dto.estoque;

import java.io.Serializable;

public class EstoqueRequestDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long quantidade;
    
    public EstoqueRequestDTO() {
        
    }

    public EstoqueRequestDTO(Long quantidade) {
        this.quantidade = quantidade;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }
    
}
