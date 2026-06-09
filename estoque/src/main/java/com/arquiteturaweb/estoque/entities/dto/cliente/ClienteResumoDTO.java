package com.arquiteturaweb.estoque.entities.dto.cliente;

import java.io.Serializable;

import com.arquiteturaweb.estoque.entities.Cliente;

public class ClienteResumoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private String cnpj;

    public ClienteResumoDTO() {

    }

    public ClienteResumoDTO(Long id, String nome, String cnpj) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
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

    public static ClienteResumoDTO converterCliente(Cliente cliente) {
        ClienteResumoDTO resumoObj  = new ClienteResumoDTO();
        resumoObj.setId(cliente.getId());
        resumoObj.setNome(cliente.getNome());
        resumoObj.setCnpj(cliente.getCnpj());
        return resumoObj;
    }

}
