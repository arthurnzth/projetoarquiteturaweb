package com.arquiteturaweb.estoque.entities.dto.cliente;

import java.io.Serializable;

import com.arquiteturaweb.estoque.entities.Cliente;

public class ClienteResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private String cnpj;
    private String contato;

    public ClienteResponseDTO() {

    }

    public ClienteResponseDTO(Long id, String nome, String cnpj, String contato) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.contato = contato;
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

    public static ClienteResponseDTO converterCliente(Cliente cliente) {
        ClienteResponseDTO responseObj = new ClienteResponseDTO();
        responseObj.setId(cliente.getId());
        responseObj.setNome(cliente.getNome());
        responseObj.setCnpj(cliente.getCnpj());
        responseObj.setContato(cliente.getContato());
        return responseObj;
    }

}