package com.arquiteturaweb.estoque.entities.dto.estoque;

import java.io.Serializable;

import com.arquiteturaweb.estoque.entities.Estoque;
import com.arquiteturaweb.estoque.entities.dto.produto.ProdutoResumoDTO;

public class EstoqueResponseDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long id;
    private Integer quantidade;
    private ProdutoResumoDTO produto;

    public EstoqueResponseDTO() {

    }

    public EstoqueResponseDTO(Long id, Integer quantidade, ProdutoResumoDTO produto) {
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

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public ProdutoResumoDTO getProduto() {
        return produto;
    }

    public void setProduto(ProdutoResumoDTO produto) {
        this.produto = produto;
    }

    public static EstoqueResponseDTO converterEstoque(Estoque estoque) {
        EstoqueResponseDTO responseDTO = new EstoqueResponseDTO();
        responseDTO.setId(estoque.getId());
        responseDTO.setProduto(ProdutoResumoDTO.converterProduto(estoque.getProduto()));
        responseDTO.setQuantidade(estoque.getQuantidade());
        return responseDTO;
    }

}
