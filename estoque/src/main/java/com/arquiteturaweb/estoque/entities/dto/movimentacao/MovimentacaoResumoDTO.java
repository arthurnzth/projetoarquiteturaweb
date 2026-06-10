package com.arquiteturaweb.estoque.entities.dto.movimentacao;

import java.io.Serializable;

import com.arquiteturaweb.estoque.entities.Movimentacao;
import com.arquiteturaweb.estoque.entities.dto.usuario.UsuarioResumoDTO;

public class MovimentacaoResumoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String tipo;
    private UsuarioResumoDTO responsavel;

    public MovimentacaoResumoDTO() {
        
    }

    public MovimentacaoResumoDTO(Long id, String tipo, UsuarioResumoDTO responsavel) {
        this.id = id;
        this.tipo = tipo;
        this.responsavel = responsavel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public static MovimentacaoResumoDTO converterMovimentacao(Movimentacao movimentacao) {
        MovimentacaoResumoDTO resumoObj = new MovimentacaoResumoDTO();
        resumoObj.setId(movimentacao.getId());
        resumoObj.setTipo(movimentacao.getTipo().name());
        resumoObj.setResponsavel(UsuarioResumoDTO.converterUsuario(movimentacao.getResponsavel()));
        return resumoObj;
    }

}
