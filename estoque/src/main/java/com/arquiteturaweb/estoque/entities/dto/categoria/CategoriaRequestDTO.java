package com.arquiteturaweb.estoque.entities.dto.categoria;

public class CategoriaRequestDTO {

    private String nome;

    public CategoriaRequestDTO() {
        
    }

    public CategoriaRequestDTO(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
