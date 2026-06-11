package com.arquiteturaweb.estoque.entities.dto.pedido;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.arquiteturaweb.estoque.entities.Pedido;
import com.arquiteturaweb.estoque.entities.dto.fornecedor.FornecedorResumoDTO;
import com.arquiteturaweb.estoque.entities.dto.itemPedido.ItemPedidoResumoDTO;
import com.arquiteturaweb.estoque.entities.dto.movimentacao.MovimentacaoResumoDTO;
import com.arquiteturaweb.estoque.entities.dto.usuario.UsuarioResumoDTO;

public class PedidoResponseDTO implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private Long id;
    private FornecedorResumoDTO fornecedor;
    private Double valorTotal;
    private String data;
    private MovimentacaoResumoDTO movimentacao;
    private UsuarioResumoDTO responsavel;
    private Set<ItemPedidoResumoDTO> itens = new HashSet<>();

    public PedidoResponseDTO(){

    }

    public PedidoResponseDTO(Long id, FornecedorResumoDTO fornecedor, Double valorTotal, String data, MovimentacaoResumoDTO movimentacao, UsuarioResumoDTO responsavel) {
        this.id = id;
        this.fornecedor = fornecedor;
        this.valorTotal = valorTotal;
        this.data = data;
        this.movimentacao = movimentacao;
        this.responsavel = responsavel;
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

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public MovimentacaoResumoDTO getMovimentacao() {
        return movimentacao;
    }

    public void setMovimentacao(MovimentacaoResumoDTO movimentacao) {
        this.movimentacao = movimentacao;
    }

    public UsuarioResumoDTO getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(UsuarioResumoDTO responsavel) {
        this.responsavel = responsavel;
    }

    public Set<ItemPedidoResumoDTO> getItens() {
        return itens;
    }

    public static PedidoResponseDTO converterPedido(Pedido pedido) {
        PedidoResponseDTO responseObj = new PedidoResponseDTO();
        responseObj.setId(pedido.getIdPedido());
        responseObj.setFornecedor(FornecedorResumoDTO.converterFornecedor(pedido.getFornecedorPedido()));
        responseObj.setValorTotal(pedido.getTotal());
        responseObj.setData(pedido.getDataPedido().toString());
        if (pedido.getMovimentacaoPedido() != null) {
            responseObj.setMovimentacao(MovimentacaoResumoDTO.converterMovimentacao(pedido.getMovimentacaoPedido()));
        }
        responseObj.setResponsavel(UsuarioResumoDTO.converterUsuario(pedido.getResponsavel()));
        responseObj.getItens().addAll(pedido.getItens().stream().<ItemPedidoResumoDTO>map(i -> ItemPedidoResumoDTO.converterItemPedido(i)).collect(Collectors.toList()));
        return responseObj;
    }

}
