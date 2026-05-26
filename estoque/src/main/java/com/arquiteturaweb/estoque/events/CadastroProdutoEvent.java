package com.arquiteturaweb.estoque.events;

import com.arquiteturaweb.estoque.entities.Produto;

public class CadastroProdutoEvent {

    private Long id;
    private String nome;
    private String descricao;
    private Double preco;

    public CadastroProdutoEvent(Produto obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.descricao = obj.getDescricao();
        this.preco = obj.getPreco();
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

}
