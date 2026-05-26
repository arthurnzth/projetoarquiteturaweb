package com.arquiteturaweb.estoque.entities.dto;

import java.util.List;

public class ProdutoRequestDTO {

    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
    private List<Long> categoriasId;
    private Long fornecedorId;

    public ProdutoRequestDTO() {

    }

    public ProdutoRequestDTO(Long id, String nome, String descricao, Double preco, List<Long> categoriasId, Long fornecedorId) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoriasId = categoriasId;
        this.fornecedorId = fornecedorId;
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

    public List<Long> getCategoriasId() {
        return categoriasId;
    }

    public Long getFornecedorId() {
        return fornecedorId;
    }

    public void setFornecedorId(Long fornecedorId) {
        this.fornecedorId = fornecedorId;
    }

}
