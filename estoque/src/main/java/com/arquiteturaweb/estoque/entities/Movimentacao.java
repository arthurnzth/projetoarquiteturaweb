package com.arquiteturaweb.estoque.entities;

import java.io.Serializable;
import java.time.Instant;

import com.arquiteturaweb.estoque.entities.enums.TipoMovimentacao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_movimentacao")
public class Movimentacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant data;
    private Integer tipo;

    @OneToOne
    @JoinColumn(name = "venda_id")
    private Venda venda;

    @OneToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario responsavel;

    private String observacao;

    public Movimentacao() {

    }

    public Movimentacao(Long id, Instant data, Object entidade, Usuario responsavel, String observacao) {
        this.id = id;
        this.data = data;
        if (entidade.getClass() == Venda.class) {
            setTipo(TipoMovimentacao.SAIDA);
        }
        else if (entidade.getClass() == Pedido.class) {
            setTipo(TipoMovimentacao.ENTRADA);
        }
        else {
            throw new IllegalArgumentException("Tipo de objeto de movimentação inválido");
        }
        this.responsavel = responsavel;
        this.observacao = observacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getData() {
        return data;
    }

    public void setData(Instant data) {
        this.data = data;
    }

    public TipoMovimentacao getTipo() {
        return TipoMovimentacao.valueOf(tipo);
    }

    public void setTipo(TipoMovimentacao tipo) {
        if (tipo != null) {
            this.tipo = tipo.getCode();
        }
    }

    public Usuario getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Usuario responsavel) {
        this.responsavel = responsavel;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
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
        Movimentacao other = (Movimentacao) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
