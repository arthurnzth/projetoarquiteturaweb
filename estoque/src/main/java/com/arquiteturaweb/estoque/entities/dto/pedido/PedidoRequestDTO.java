package com.arquiteturaweb.estoque.entities.dto.pedido;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.arquiteturaweb.estoque.entities.dto.itemPedido.ItemPedidoRequestDTO;

public class PedidoRequestDTO implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private Long fornecedorId;
    private String data;
    private Long responsavelId;
    private List<ItemPedidoRequestDTO> itens = new ArrayList<>();
    private String observacao;

    public PedidoRequestDTO(){

    }

    public PedidoRequestDTO(Long fornecedorId, String data, Long responsavelId, List<ItemPedidoRequestDTO> itens, String observacao){
        this.fornecedorId = fornecedorId;
        this.data = data;
        this.responsavelId = responsavelId;
        this.itens = itens;
        this.observacao = observacao;
    }

    public Long getFornecedorId() {
        return fornecedorId;
    }

    public void setFornecedorId(Long fornecedorId) {
        this.fornecedorId = fornecedorId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Long getResponsavelId() {
        return responsavelId;
    }

    public void setResponsavelId(Long responsavelId) {
        this.responsavelId = responsavelId;
    }

    public List<ItemPedidoRequestDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoRequestDTO> itens) {
        this.itens = itens;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

}
