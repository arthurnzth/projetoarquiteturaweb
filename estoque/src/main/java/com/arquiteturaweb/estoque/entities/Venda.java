package com.arquiteturaweb.estoque.entities;

import java.io.Serializable;
import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    private Double valorTotal;

    private Instant data;

    @OneToOne(mappedBy = "movimentacao")
    private Movimentacao movimentacao;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario responsavel;

    public Venda (){

    }

    public Venda (Long id, Cliente cliente, Double valorTotal, Instant data, Movimentacao movimentacao, Usuario responsavel){
        this.id = id;
        this.cliente = cliente;
        this.valorTotal = valorTotal;
        this.data = data;
        this.movimentacao = movimentacao;
        this.responsavel = responsavel;
    }

    

}
