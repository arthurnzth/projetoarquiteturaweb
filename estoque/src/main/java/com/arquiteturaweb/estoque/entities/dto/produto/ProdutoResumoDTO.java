package com.arquiteturaweb.estoque.entities.dto.produto;

import java.io.Serializable;

import com.arquiteturaweb.estoque.entities.Produto;

public class ProdutoResumoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private Double preco;

    public ProdutoResumoDTO() {

    }

    public ProdutoResumoDTO(Long id, String nome, Double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }
    
    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public static ProdutoResumoDTO converterProduto(Produto produto) {
        ProdutoResumoDTO resumoObj = new ProdutoResumoDTO();
        resumoObj.setId(produto.getId());
        resumoObj.setNome(produto.getNome());
        resumoObj.setPreco(produto.getPreco());
        return resumoObj;
    }

}
