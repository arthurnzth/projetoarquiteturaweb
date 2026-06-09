package com.arquiteturaweb.estoque.entities.dto.usuario;

import java.io.Serializable;

import com.arquiteturaweb.estoque.entities.Usuario;

public class UsuarioResumoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private String cargo;
    private String ativo;

    public UsuarioResumoDTO() {

    }

    public UsuarioResumoDTO(Long id, String nome, String cargo, String ativo) {
        this.id = id;
        this.nome = nome;
        this.cargo = cargo;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public static UsuarioResumoDTO converterUsuario(Usuario usuario) {
        UsuarioResumoDTO resumoObj = new UsuarioResumoDTO();
        resumoObj.setId(usuario.getId());
        resumoObj.setNome(usuario.getNome());
        resumoObj.setCargo(usuario.getCargo());
        resumoObj.setAtivo(usuario.isAtivo() ? "ativo" : "desativado");
        return resumoObj;
    }

}