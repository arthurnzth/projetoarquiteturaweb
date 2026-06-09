package com.arquiteturaweb.estoque.entities.dto.pedido;

import java.io.Serializable;

import com.arquiteturaweb.estoque.entities.Pedido;

public class PedidoResumoDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long id;
    private Double valorTotal;

    public Long getIdPedidoResumo() {
        return id;
    }
    public void setIdPedidoResumo(Long id) {
        this.id = id;
    }
    public Double getValorTotalPedidoResumo() {
        return valorTotal;
    }
    public void setValorTotalPedidoResumo(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public static PedidoResumoDTO converterPedido(Pedido pedido) {
        PedidoResumoDTO resumoObj = new PedidoResumoDTO();
        resumoObj.setIdPedidoResumo(pedido.getIdPedido());
        resumoObj.setValorTotalPedidoResumo(pedido.getValorTotalPedido());
        return resumoObj;
    }
}
