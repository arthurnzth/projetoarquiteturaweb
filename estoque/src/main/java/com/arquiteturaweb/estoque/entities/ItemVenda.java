package com.arquiteturaweb.estoque.entities;

import java.io.Serializable;

import com.arquiteturaweb.estoque.entities.pk.ItemVendaPK;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_item_venda")
public class ItemVenda implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ItemVendaPK id = new ItemVendaPK();

    private Integer quantidade;
    private Double preco;

    public ItemVenda() {
        
    }

    public ItemVenda(Venda venda, Produto produto, Integer quantidade, Double preco) {
        this.id.setVenda(venda);
        this.id.setProduto(produto);
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public Venda getVenda() {
        return id.getVenda();
    }
    
    public void setVenda(Venda venda) {
        id.setVenda(venda);
    }

    public Produto getProduto() {
        return id.getProduto();
    }

    public void setProduto(Produto produto) {
        id.setProduto(produto);
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Double getSubTotal() {
        return preco * quantidade;
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
        ItemVenda other = (ItemVenda) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
