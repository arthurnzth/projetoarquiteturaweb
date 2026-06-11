package com.arquiteturaweb.estoque.entities.dto.venda;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.arquiteturaweb.estoque.entities.Venda;
import com.arquiteturaweb.estoque.entities.dto.cliente.ClienteResumoDTO;
import com.arquiteturaweb.estoque.entities.dto.itemVenda.ItemVendaResumoDTO;

public class VendaResumoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private ClienteResumoDTO cliente;
    private String data;
    private Set<ItemVendaResumoDTO> itens = new HashSet<>();

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

    public Set<ItemVendaResumoDTO> getItens() {
        return itens;
    }

    public static VendaResumoDTO converterVenda(Venda venda) {
        VendaResumoDTO resumoObj = new VendaResumoDTO();
        resumoObj.setId(venda.getIdVenda());
        resumoObj.setCliente(ClienteResumoDTO.converterCliente(venda.getClienteVenda()));
        resumoObj.setData(venda.getDataVenda().toString());
        resumoObj.getItens().addAll(venda.getItens().stream().<ItemVendaResumoDTO>map(i -> ItemVendaResumoDTO.converterItemVenda(i)).collect(Collectors.toList()));
        return resumoObj;
    }

}
