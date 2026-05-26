package com.arquiteturaweb.estoque.entities.dto.categoria;

import java.util.Set;

import com.arquiteturaweb.estoque.entities.dto.produto.ProdutoResumoDTO;

public class CategoriaResponseDTO {

    private Long id;
    private String nome;
    private Set<ProdutoResumoDTO> produtos;

    public CategoriaResponseDTO() {

    }

    public CategoriaResponseDTO(Long id, String nome, Set<ProdutoResumoDTO> produtos) {
        this.id = id;
        this.nome = nome;
        this.produtos = produtos;
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

    public Set<ProdutoResumoDTO> getProdutos() {
        return produtos;
    }

    public void setProdutos(Set<ProdutoResumoDTO> produtos) {
        this.produtos = produtos;
    }

}
