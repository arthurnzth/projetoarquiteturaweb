package com.arquiteturaweb.estoque.entities.dto.estoque;

import com.arquiteturaweb.estoque.entities.dto.produto.ProdutoResumoDTO;

public class EstoqueResponseDTO {

    private Long id;
    private Long quantidade;
    private ProdutoResumoDTO produto;

    public EstoqueResponseDTO() {

    }

    public EstoqueResponseDTO(Long id, Long quantidade, ProdutoResumoDTO produto) {
        this.id = id;
        this.quantidade = quantidade;
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    public ProdutoResumoDTO getProduto() {
        return produto;
    }

    public void setProduto(ProdutoResumoDTO produto) {
        this.produto = produto;
    }

}
