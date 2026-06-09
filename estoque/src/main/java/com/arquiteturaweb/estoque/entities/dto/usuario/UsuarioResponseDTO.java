package com.arquiteturaweb.estoque.entities.dto.usuario;

import java.io.Serializable;

import com.arquiteturaweb.estoque.entities.Usuario;

public class UsuarioResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private String email;
    private String cargo;
    private String ativo;

    public UsuarioResponseDTO() {

    }

    public UsuarioResponseDTO(Long id, String nome, String email, String cargo, String ativo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public static UsuarioResponseDTO converterUsuario(Usuario usuario) {
        UsuarioResponseDTO responseObj = new UsuarioResponseDTO();
        responseObj.setId(usuario.getId());
        responseObj.setNome(usuario.getNome());
        responseObj.setEmail(usuario.getEmail());
        responseObj.setCargo(usuario.getCargo());
        responseObj.setAtivo(usuario.isAtivo() ? "ativo" : "desativado");
        return responseObj;
    }

}