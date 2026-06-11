package com.arquiteturaweb.estoque.entities.dto.movimentacao;

import java.io.Serializable;

public class MovimentacaoRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String observacao;

    public MovimentacaoRequestDTO() {

    }

    public MovimentacaoRequestDTO(String observacao) {
        this.observacao = observacao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

}
