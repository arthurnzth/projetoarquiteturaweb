package com.arquiteturaweb.estoque.entities.dto.produto;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.arquiteturaweb.estoque.entities.Produto;
import com.arquiteturaweb.estoque.entities.dto.categoria.CategoriaResumoDTO;
import com.arquiteturaweb.estoque.entities.dto.estoque.EstoqueResumoDTO;
import com.arquiteturaweb.estoque.entities.dto.fornecedor.FornecedorResumoDTO;

public class ProdutoResponseDTO {

    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
    private Set<CategoriaResumoDTO> categorias = new HashSet<>();
    private FornecedorResumoDTO fornecedor;
    private EstoqueResumoDTO estoque;

    public ProdutoResponseDTO() {
        
    }

    public ProdutoResponseDTO(Long id, String nome, String descricao, Double preco, FornecedorResumoDTO fornecedor, EstoqueResumoDTO estoque) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.fornecedor = fornecedor;
        this.estoque = estoque;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Set<CategoriaResumoDTO> getCategorias() {
        return categorias;
    }

    public FornecedorResumoDTO getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(FornecedorResumoDTO fornecedor) {
        this.fornecedor = fornecedor;
    }

    public EstoqueResumoDTO getEstoque() {
        return estoque;
    }

    public void setEstoque(EstoqueResumoDTO estoque) {
        this.estoque = estoque;
    }

    public static ProdutoResponseDTO converterProduto(Produto produto) {
        ProdutoResponseDTO responseObj = new ProdutoResponseDTO();
        responseObj.setId(produto.getId());
        responseObj.setNome(produto.getNome());
        responseObj.setDescricao(produto.getDescricao());
        responseObj.setPreco(produto.getPreco());
        responseObj.getCategorias().addAll(produto.getCategorias().stream().<CategoriaResumoDTO>map(c -> CategoriaResumoDTO.converterCategoria(c)).collect(Collectors.toList()));
        responseObj.setFornecedor(FornecedorResumoDTO.converterFornecedor(produto.getFornecedor()));
        responseObj.setEstoque(EstoqueResumoDTO.converterEstoque(produto.getEstoque()));
        return responseObj;
    }

}
