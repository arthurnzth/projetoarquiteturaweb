package com.arquiteturaweb.estoque.entities.dto.usuario;

import java.io.Serializable;

public class UsuarioRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;
    private String email;
    private String cargo;
    private Byte ativo;

    public UsuarioRequestDTO() {

    }

    public UsuarioRequestDTO(String nome, String email, String cargo, Byte ativo) {
        this.nome = nome;
        this.email = email;
        this.cargo = cargo;
        this.ativo = ativo;
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

    public Byte getAtivo() {
        return ativo;
    }

    public void setAtivo(Byte ativo) {
        this.ativo = ativo;
    }

}