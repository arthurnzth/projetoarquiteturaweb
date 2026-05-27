package com.arquiteturaweb.estoque.entities.dto.categoria;

import com.arquiteturaweb.estoque.entities.Categoria;

public class CategoriaResumoDTO {

    private Long id;
    private String nome;

    public CategoriaResumoDTO() {

    }

    public CategoriaResumoDTO(Long id, String nome) {
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

    public static CategoriaResumoDTO converterCategoria(Categoria categoria) {
        CategoriaResumoDTO resumoObj = new CategoriaResumoDTO();
        resumoObj.setId(categoria.getId());
        resumoObj.setNome(categoria.getNome());
        return resumoObj;
    }

}
