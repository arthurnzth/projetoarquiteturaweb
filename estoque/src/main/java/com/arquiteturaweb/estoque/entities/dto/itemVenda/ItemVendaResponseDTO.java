package com.arquiteturaweb.estoque.entities.dto.itemVenda;

import java.io.Serializable;

import com.arquiteturaweb.estoque.entities.ItemVenda;
import com.arquiteturaweb.estoque.entities.dto.produto.ProdutoResumoDTO;

public class ItemVendaResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private ProdutoResumoDTO produto;
    private Integer quantidade;
    private Double subTotal;

    public ItemVendaResponseDTO() {
        
    }

    public ItemVendaResponseDTO(ProdutoResumoDTO produto, Integer quantidade, Double subTotal) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.subTotal = subTotal;
    }

    public ProdutoResumoDTO getProduto() {
        return produto;
    }

    public void setProduto(ProdutoResumoDTO produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public static ItemVendaResponseDTO converterItemVenda(ItemVenda item) {
        ItemVendaResponseDTO resumoObj = new ItemVendaResponseDTO();
        resumoObj.setProduto(ProdutoResumoDTO.converterProduto(item.getProduto()));
        resumoObj.setQuantidade(item.getQuantidade());
        resumoObj.setSubTotal(item.getSubTotal());
        return resumoObj;
    }

}
