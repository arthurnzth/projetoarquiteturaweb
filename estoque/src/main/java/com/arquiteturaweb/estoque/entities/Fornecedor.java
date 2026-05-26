package com.arquiteturaweb.estoque.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_fornecedor")
public class Fornecedor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String endereco;
    private String telefone;

    @OneToMany(mappedBy = "fornecedor")
    private Set<Produto> produtos = new HashSet<>();

    public Fornecedor(){

    }

    public Fornecedor(Long id, String nome, String endereco, String telefone){
        
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public long getIdFornecedor(){
        return id;
    }

    public void setIdFornecedor(long id){
        this.id = id;
    }

    public String getNomeFornecedor(){
        return nome;
    }

    public void setNomeFornecedor(String nome){
        this.nome = nome;
    }

    public String getEnderecoFornecedor() {
        return endereco;
    }

    public void setEnderecoFornecedor(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefoneFornecedor() {
        return telefone;
    }

    public void setTelefoneFornecedor(String telefone) {
        this.telefone = telefone;
    }

    public Set<Produto> getProdutos() {
        return produtos;
    }

    //Analisar melhor depois
    public void setProdutos(Set<Produto> produtos) {
        this.produtos = produtos;
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
        Fornecedor other = (Fornecedor) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
