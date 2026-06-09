package com.arquiteturaweb.estoque.entities.dto.cliente;

import java.io.Serializable;

public class ClienteRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;
    private String cnpj;
    private String contato;

    public ClienteRequestDTO() {

    }

    public ClienteRequestDTO(String nome, String cnpj, String contato) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.contato = contato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

}