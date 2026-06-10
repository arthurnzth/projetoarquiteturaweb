package com.arquiteturaweb.estoque.entities.dto.pedido;

import java.io.Serializable;

public class PedidoRequestDTO implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private Double valorTotal;
    private Long fornecedorId;
    private Long movimentacaoId;

    public PedidoRequestDTO(){

    }

    public PedidoRequestDTO(Double valorTotal, Long fornecedorId, Long movimentacaoId){
        this.valorTotal = valorTotal;
        this.fornecedorId = fornecedorId;
        this.movimentacaoId = movimentacaoId;
    }


    public Double getValorTotalRequestPedido() {
        return valorTotal;
    }

    public void setValorTotalRequestPedido(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Long getFornecedorIdPedidoRequest() {
        return fornecedorId;
    }

    public void setFornecedorIdPedidoRequest(Long fornecedorId) {
        this.fornecedorId = fornecedorId;
    }

    public Long getMovimentacaoIdPedidoRequest() {
        return movimentacaoId;
    }

    public void setMovimentacaoIdPedidoRequest(Long movimentacaoId) {
        this.movimentacaoId = movimentacaoId;
    }


}
