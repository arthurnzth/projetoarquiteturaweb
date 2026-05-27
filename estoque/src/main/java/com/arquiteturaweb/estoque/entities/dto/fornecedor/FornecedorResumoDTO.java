package com.arquiteturaweb.estoque.entities.dto.fornecedor;

import java.io.Serializable;

import com.arquiteturaweb.estoque.entities.Fornecedor;

public class FornecedorResumoDTO implements Serializable{

    private static final long serialVersionUID = 1L;

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

    public static FornecedorResumoDTO converterFornecedor(Fornecedor fornecedor) {
        FornecedorResumoDTO resumoObj = new FornecedorResumoDTO();
        resumoObj.setId(fornecedor.getIdFornecedor());
        resumoObj.setNome(fornecedor.getNomeFornecedor());
        return resumoObj;
    }
    
}
