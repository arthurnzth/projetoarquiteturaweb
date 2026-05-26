package com.arquiteturaweb.estoque.entities.dto.fornecedor;

import java.util.Set;

import com.arquiteturaweb.estoque.entities.dto.produto.ProdutoResponseDTO;

public class FornecedorResponseDTO {

    private Long id;
    private String nome;
    private String endereco;
    private String telefone;
    private Set<ProdutoResponseDTO> produtos;

    public FornecedorResponseDTO() {

    }

    public FornecedorResponseDTO(Long id, String nome, String endereco, String telefone, Set<ProdutoResponseDTO> produtos) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
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
    
    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Set<ProdutoResponseDTO> getProdutos() {
        return produtos;
    }

    public void setProdutos(Set<ProdutoResponseDTO> produtos) {
        this.produtos = produtos;
    }

}
