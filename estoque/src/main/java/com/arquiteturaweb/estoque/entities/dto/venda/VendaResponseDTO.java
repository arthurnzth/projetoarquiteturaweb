package com.arquiteturaweb.estoque.entities.dto.venda;

import java.io.Serializable;

import com.arquiteturaweb.estoque.entities.Venda;
import com.arquiteturaweb.estoque.entities.dto.cliente.ClienteResumoDTO;
import com.arquiteturaweb.estoque.entities.dto.movimentacao.MovimentacaoResumoDTO;
import com.arquiteturaweb.estoque.entities.dto.usuario.UsuarioResumoDTO;

public class VendaResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private ClienteResumoDTO cliente;
    private Double valorTotal;
    private String data;
    private MovimentacaoResumoDTO movimentacao;
    private UsuarioResumoDTO usuario;

    public VendaResponseDTO() {

    }

    public VendaResponseDTO(Long id, ClienteResumoDTO cliente, Double valorTotal, String data, MovimentacaoResumoDTO movimentacao, UsuarioResumoDTO usuario) {
        this.id = id;
        this.cliente = cliente;
        this.valorTotal = valorTotal;
        this.data = data;
        this.movimentacao = movimentacao;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClienteResumoDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteResumoDTO cliente) {
        this.cliente = cliente;
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

    public UsuarioResumoDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioResumoDTO usuario) {
        this.usuario = usuario;
    }

    public static VendaResponseDTO converterVenda(Venda venda) {
        VendaResponseDTO responseObj = new VendaResponseDTO();
        responseObj.setId(venda.getIdVenda());
        responseObj.setCliente(ClienteResumoDTO.converterCliente(venda.getClienteVenda()));
        responseObj.setValorTotal(venda.getTotal());
        responseObj.setData(venda.getDataVenda().toString());
        if (venda.getMovimentacaoVenda() != null) {
            responseObj.setMovimentacao(MovimentacaoResumoDTO.converterMovimentacao(venda.getMovimentacaoVenda()));
        }
        responseObj.setUsuario(UsuarioResumoDTO.converterUsuario(venda.getResponsavelVenda()));
        return responseObj;
    }

}
