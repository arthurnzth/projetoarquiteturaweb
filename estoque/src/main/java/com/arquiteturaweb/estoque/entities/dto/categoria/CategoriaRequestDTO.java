package com.arquiteturaweb.estoque.entities.dto.categoria;

import java.io.Serializable;

public class CategoriaRequestDTO implements Serializable{

    private static final long serialVersionUID = 1L;

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
