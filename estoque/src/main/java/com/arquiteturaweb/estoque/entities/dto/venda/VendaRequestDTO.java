package com.arquiteturaweb.estoque.entities.dto.venda;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.arquiteturaweb.estoque.entities.dto.itemVenda.ItemVendaRequestDTO;

public class VendaRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long clienteId;
    private String data;
    private Long responsavelId;
    private List<ItemVendaRequestDTO> itens = new ArrayList<>();

    public VendaRequestDTO() {

    }

    public VendaRequestDTO(Long clienteId, String data, Long responsavelId, List<ItemVendaRequestDTO> itens) {
        this.clienteId = clienteId;
        this.data = data;
        this.responsavelId = responsavelId;
        this.itens = itens;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Long getResponsavelId() {
        return responsavelId;
    }

    public void setResponsavelId(Long responsavelId) {
        this.responsavelId = responsavelId;
    }

    public List<ItemVendaRequestDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemVendaRequestDTO> itens) {
        this.itens = itens;
    }

}
