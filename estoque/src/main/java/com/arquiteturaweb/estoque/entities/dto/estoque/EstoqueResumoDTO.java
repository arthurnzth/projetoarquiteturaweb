package com.arquiteturaweb.estoque.entities.dto.estoque;

import java.io.Serializable;

import com.arquiteturaweb.estoque.entities.Estoque;

public class EstoqueResumoDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long id;
    private String produtoNome;
    private Integer quantidade;

    public EstoqueResumoDTO() {

    }
    
    public EstoqueResumoDTO(Long id, String produtoNome, Integer quantidade) {
        this.id = id;
        this.produtoNome = produtoNome;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        
        this.id = id;
    }
    public String getProdutoNome() {
        return produtoNome;
    }

    public void setProdutoNome(String produtoNome) {
        this.produtoNome = produtoNome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public static EstoqueResumoDTO converterEstoque(Estoque estoque) {
        EstoqueResumoDTO resumoObj = new EstoqueResumoDTO();
        resumoObj.setId(estoque.getId());
        resumoObj.setProdutoNome(estoque.getProduto().getNome());
        resumoObj.setQuantidade(estoque.getQuantidade());
        return resumoObj;
    }

}
