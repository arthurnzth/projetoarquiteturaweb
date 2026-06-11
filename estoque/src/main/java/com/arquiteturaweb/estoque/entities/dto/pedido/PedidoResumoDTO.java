package com.arquiteturaweb.estoque.entities.dto.pedido;

import java.io.Serializable;

import com.arquiteturaweb.estoque.entities.Pedido;
import com.arquiteturaweb.estoque.entities.dto.fornecedor.FornecedorResumoDTO;

public class PedidoResumoDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long id;
    private FornecedorResumoDTO fornecedor;
    private String data;

    public PedidoResumoDTO() {

    }

    public PedidoResumoDTO(Long id, FornecedorResumoDTO fornecedor, String data) {
        this.id = id;
        this.fornecedor = fornecedor;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FornecedorResumoDTO getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(FornecedorResumoDTO fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public static PedidoResumoDTO converterPedido(Pedido pedido) {
        PedidoResumoDTO resumoObj = new PedidoResumoDTO();
        resumoObj.setId(pedido.getIdPedido());
        resumoObj.setFornecedor(FornecedorResumoDTO.converterFornecedor(pedido.getFornecedorPedido()));
        resumoObj.setData(pedido.getDataPedido().toString());
        return resumoObj;
    }

}
