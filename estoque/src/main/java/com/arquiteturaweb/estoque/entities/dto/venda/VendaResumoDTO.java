package com.arquiteturaweb.estoque.entities.dto.venda;

import java.io.Serializable;

import com.arquiteturaweb.estoque.entities.Venda;
import com.arquiteturaweb.estoque.entities.dto.cliente.ClienteResumoDTO;

public class VendaResumoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private ClienteResumoDTO cliente;
    private String data;

    public VendaResumoDTO() {
        
    }

    public VendaResumoDTO(Long id, ClienteResumoDTO cliente, String data) {
        this.id = id;
        this.cliente = cliente;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClienteResumoDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteResumoDTO cliente) {
        this.cliente = cliente;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public static VendaResumoDTO converterVenda(Venda venda) {
        VendaResumoDTO resumoObj = new VendaResumoDTO();
        resumoObj.setId(venda.getIdVenda());
        resumoObj.setCliente(ClienteResumoDTO.converterCliente(venda.getClienteVenda()));
        resumoObj.setData(venda.getDataVenda().toString());
        return resumoObj;
    }

}
