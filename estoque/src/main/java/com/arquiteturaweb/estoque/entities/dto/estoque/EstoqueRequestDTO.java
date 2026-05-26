package com.arquiteturaweb.estoque.entities.dto.estoque;

public class EstoqueRequestDTO {

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
