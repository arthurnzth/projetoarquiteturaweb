package com.arquiteturaweb.estoque.entities.dto.pedido;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.arquiteturaweb.estoque.entities.Pedido;
import com.arquiteturaweb.estoque.entities.dto.fornecedor.FornecedorResumoDTO;
import com.arquiteturaweb.estoque.entities.dto.itemPedido.ItemPedidoResumoDTO;

public class PedidoResumoDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long id;
    private FornecedorResumoDTO fornecedor;
    private String data;
    private Set<ItemPedidoResumoDTO> itens = new HashSet<>();

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

    public Set<ItemPedidoResumoDTO> getItens() {
        return itens;
    }

    public static PedidoResumoDTO converterPedido(Pedido pedido) {
        PedidoResumoDTO resumoObj = new PedidoResumoDTO();
        resumoObj.setId(pedido.getIdPedido());
        resumoObj.setFornecedor(FornecedorResumoDTO.converterFornecedor(pedido.getFornecedorPedido()));
        resumoObj.setData(pedido.getDataPedido().toString());
        resumoObj.getItens().addAll(pedido.getItens().stream().<ItemPedidoResumoDTO>map(i -> ItemPedidoResumoDTO.converterItemPedido(i)).collect(Collectors.toList()));
        return resumoObj;
    }

}
