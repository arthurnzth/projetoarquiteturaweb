package com.arquiteturaweb.estoque.entities.dto.cliente;
public class ClienteRequestDTO {

    private String nome;
    private String cnpj;
    private String endereco;
    private String contato;

    public ClienteRequestDTO() {

    }

    public ClienteRequestDTO(String nome, String cnpj, String endereco, String contato) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }
}