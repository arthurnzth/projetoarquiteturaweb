package com.arquiteturaweb.estoque.entities.dto.fornecedor;

public class FornecedorResumoDTO {

    private Long id;
    private String nome;

    public FornecedorResumoDTO() {

    }

    public FornecedorResumoDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
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
    
}
