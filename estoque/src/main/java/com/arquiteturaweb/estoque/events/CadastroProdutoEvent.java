package com.arquiteturaweb.estoque.events;

public class CadastroProdutoEvent {

    private Long produtoId;

    public CadastroProdutoEvent() {

    }

    public CadastroProdutoEvent(Long produtoId) {
        this.produtoId = produtoId;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

}
