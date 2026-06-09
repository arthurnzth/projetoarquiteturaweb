package com.arquiteturaweb.estoque.entities.dto.usuario;

public class UsuarioRequestDTO {

    private String nome;
    private String email;
    private String cargo;
    private Boolean ativo;

    public UsuarioRequestDTO() {

    }

    public UsuarioRequestDTO(String nome, String email, String cargo, Boolean ativo) {
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

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}