package com.arquiteturaweb.estoque.entities.dto.movimentacao;

import java.io.Serializable;

import com.arquiteturaweb.estoque.entities.Movimentacao;
import com.arquiteturaweb.estoque.entities.dto.pedido.PedidoResumoDTO;
import com.arquiteturaweb.estoque.entities.dto.usuario.UsuarioResumoDTO;
import com.arquiteturaweb.estoque.entities.dto.venda.VendaResumoDTO;
import com.arquiteturaweb.estoque.entities.enums.TipoMovimentacao;

public class MovimentacaoResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String data;
    private String tipo;
    private UsuarioResumoDTO responsavel;
    private String observacao;
    private Object entidade;

    public MovimentacaoResponseDTO() {

    }

    public MovimentacaoResponseDTO(Long id, String data, String tipo, UsuarioResumoDTO responsavel, String observacao, Object entidade) {
        this.id = id;
        this.data = data;
        this.tipo = tipo;
        this.responsavel = responsavel;
        this.observacao = observacao;
        this.entidade = entidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public UsuarioResumoDTO getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(UsuarioResumoDTO responsavel) {
        this.responsavel = responsavel;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Object getEntidade() {
        return entidade;
    }

    public void setEntidade(Object entidade) {
        this.entidade = entidade;
    }

    public static MovimentacaoResponseDTO converterMovimentacao(Movimentacao movimentacao) {
        MovimentacaoResponseDTO responseObj = new MovimentacaoResponseDTO();
        responseObj.setId(movimentacao.getId());
        responseObj.setData(movimentacao.getData().toString());
        responseObj.setTipo(movimentacao.getTipo().name());
        responseObj.setResponsavel(UsuarioResumoDTO.converterUsuario(movimentacao.getResponsavel()));
        responseObj.setObservacao(movimentacao.getObservacao());
        if (movimentacao.getTipo() == TipoMovimentacao.ENTRADA) {
            responseObj.setEntidade(PedidoResumoDTO.converterPedido(movimentacao.getPedido()));
        }
        else if (movimentacao.getTipo() == TipoMovimentacao.SAIDA) {
            responseObj.setEntidade(VendaResumoDTO.converterVenda(movimentacao.getVenda()));
        }
        return responseObj;
    }

}
