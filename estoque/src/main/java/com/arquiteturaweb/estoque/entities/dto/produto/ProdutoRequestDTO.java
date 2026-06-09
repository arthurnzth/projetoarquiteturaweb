package com.arquiteturaweb.estoque.entities.dto.produto;

import java.io.Serializable;
import java.util.Set;

public class ProdutoRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;
    private String descricao;
    private Double preco;
    private Set<Long> categoriasId;
    private Long fornecedorId;

    public ProdutoRequestDTO() {

    }

    public ProdutoRequestDTO(String nome, String descricao, Double preco, Set<Long> categoriasId, Long fornecedorId) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoriasId = categoriasId;
        this.fornecedorId = fornecedorId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Set<Long> getCategoriasId() {
        return categoriasId;
    }

    public void setCategoriasId(Set<Long> categoriasId) {
        this.categoriasId = categoriasId;
    }

    public Long getFornecedorId() {
        return fornecedorId;
    }

    public void setFornecedorId(Long fornecedorId) {
        this.fornecedorId = fornecedorId;
    }

}
