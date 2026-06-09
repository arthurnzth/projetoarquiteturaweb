package com.arquiteturaweb.estoque.entities.dto.pedido;

import java.io.Serializable;
import java.util.HashSet;
import java.util.stream.Collectors;

import com.arquiteturaweb.estoque.entities.Pedido;
import com.arquiteturaweb.estoque.entities.Produto;
import com.arquiteturaweb.estoque.entities.dto.categoria.CategoriaResumoDTO;
import com.arquiteturaweb.estoque.entities.dto.estoque.EstoqueResumoDTO;
import com.arquiteturaweb.estoque.entities.dto.fornecedor.FornecedorResumoDTO;
import com.arquiteturaweb.estoque.entities.dto.produto.ProdutoResponseDTO;

public class PedidoResponseDTO implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private Long id;
    private Double valorTotal;
    private FornecedorResumoDTO fornecedor;
    private MovimentacaoResumoDTO movimentacao;

    public PedidoResponseDTO(){

    }

    public PedidoResponseDTO(Long id, Double valorTotal, FornecedorResumoDTO fornecedor, MovimentacaoResumoDTO movimentacao){
        this.id = id;
        this.valorTotal = valorTotal;
        this.fornecedor = fornecedor;
        this.movimentacao = movimentacao;
    }

    public Long getIdPedidoResponse() {
        return id;
    }

    public void setIdPedidoResponse(Long id) {
        this.id = id;
    }

    public Double getValorTotalPedidoResponse() {
        return valorTotal;
    }

    public void setValorTotalPedidoResponse(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public FornecedorResumoDTO getFornecedorPedidoResponse() {
        return fornecedor;
    }

    public void setFornecedorPedidoResponse(FornecedorResumoDTO fornecedor) {
        this.fornecedor = fornecedor;
    }

    public MovimentacaoResumoDTO getMovimentacaoPedidoResponse() {
        return movimentacao;
    }

    public void setMovimentacaoPedidoResponse(MovimentacaoResumoDTO movimentacao) {
        this.movimentacao = movimentacao;
    }

    public static PedidoResponseDTO converterPedido(Pedido pedido) {
        PedidoResponseDTO responseObj = new PedidoResponseDTO();
        responseObj.setIdPedidoResponse(pedido.getIdPedido());
        responseObj.setMovimentacaoPedidoResponse(pedido.getMovimentacaoPedido());
        responseObj.setValorTotalPedidoResponse(pedido.getValorTotalPedido());
        responseObj.setFornecedorPedidoResponse(FornecedorResumoDTO.converterFornecedor(pedido.getFornecedorPedido()));
        return responseObj;
    }

    
}
