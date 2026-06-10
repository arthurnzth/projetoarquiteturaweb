package com.arquiteturaweb.estoque.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta .persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_pedido")
public class Pedido implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double valorTotal;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor; 

    // Esperar a construção 
    // @OneToOne(mappedBy = "movimentacao")
    //private Movimentacao movimentacao;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario responsavel;

    @OneToMany(mappedBy = "id.pedido")
    private Set<ItemPedido> itens = new HashSet<>();

    public Pedido(){

    }

    //public Pedido(Long id, Double valorTotal, Fornecedor fornecedor, Movimentacao movimentacao, Usuario responsavel){
        //this.id = id;
        //this.valorTotal = valorTotal;
        //this.fornecedor = fornecedor;
        // this.movimentacao = movimentacao;
        // this.responsavel = responsavel;
    //}

    public Long getIdPedido(){
        return this.id;
    }

    public void setIdPedido(Long id_novo){
        this.id = id_novo;
    }

    public Double getValorTotalPedido(){
        return this.valorTotal;
    }

    public void setValorTotalPedido(Double valorTotalPedido){
        this.valorTotal = valorTotalPedido;
    }

    public Fornecedor getFornecedorPedido(){
        return fornecedor;
    }

    public void setFornecedorPedido (Fornecedor fornecedor){
        this.fornecedor = fornecedor;
    }

    //public Movimentacao getMovimentacaoPedido(){
    //    return movimentacao;
    //}

    //public void setMovimentacaoPedido(Movimentacao movimentacao){
    //    this.movimentacao = movimentacao;
    //} 
    
    public Usuario getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Usuario responsavel) {
        this.responsavel = responsavel;
    }

    public Set<ItemPedido> getItens() {
        return itens;
    }

    public Double getTotal() {
        double sum = 0.0;
        for (ItemPedido i : itens) {
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
        Pedido other = (Pedido) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
