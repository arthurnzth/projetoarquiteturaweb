package com.arquiteturaweb.estoque.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_vendas")
public class Venda implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant data;

    @OneToOne(mappedBy = "venda")
    private Movimentacao movimentacao;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario responsavel;

    @OneToMany(mappedBy = "id.venda")
    private Set<ItemVenda> itens = new HashSet<>();

    public Venda (){

    }

    public Venda (Long id, Cliente cliente, Instant data, Movimentacao movimentacao, Usuario responsavel){
        this.id = id;
        this.cliente = cliente;
        this.data = data;
        this.movimentacao = movimentacao;
        this.responsavel = responsavel;
    }
    
    public Long getIdVenda() {
        return id;
    }

    public void setIdVenda(Long id) {
        this.id = id;
    }

    public Cliente getClienteVenda() {
        return cliente;
    }

    public void setClienteVenda(Cliente cliente) {
        this.cliente = cliente;
    }

    public Instant getDataVenda() {
        return data;
    }

    public void setDataVenda(Instant data) {
        this.data = data;
    }
    
    public Movimentacao getMovimentacaoVenda() {
        return movimentacao;
    }

    public void setMovimentacaoVenda(Movimentacao movimentacao) {
        this.movimentacao = movimentacao;
    }
    
    public Usuario getResponsavelVenda() {
        return responsavel;
    }

    public void setResponsavelVenda(Usuario responsavel) {
        this.responsavel = responsavel;
    }

    public Set<ItemVenda> getItens() {
        return itens;
    }

    public Double getTotal() {
        double sum = 0.0;
        for (ItemVenda i : itens) {
            sum += i.getSubTotal();
        }
        return sum;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Venda other = (Venda) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
