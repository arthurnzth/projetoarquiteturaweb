package com.arquiteturaweb.estoque.entities.dto.estoque;

public class EstoqueResumoDTO {

    private Long id;
    private String produtoNome;
    private Long quantidade;

    public EstoqueResumoDTO() {

    }
    
    public EstoqueResumoDTO(Long id, String produtoNome, Long quantidade) {
        this.id = id;
        this.produtoNome = produtoNome;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        
        this.id = id;
    }
    public String getProdutoNome() {
        return produtoNome;
    }

    public void setProdutoNome(String produtoNome) {
        this.produtoNome = produtoNome;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

}
