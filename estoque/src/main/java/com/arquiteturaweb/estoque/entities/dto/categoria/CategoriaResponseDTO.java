package com.arquiteturaweb.estoque.entities.dto.categoria;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.arquiteturaweb.estoque.entities.Categoria;
import com.arquiteturaweb.estoque.entities.dto.produto.ProdutoResumoDTO;

public class CategoriaResponseDTO {

    private Long id;
    private String nome;
    private Set<ProdutoResumoDTO> produtos = new HashSet<>();

    public CategoriaResponseDTO() {

    }

    public CategoriaResponseDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
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

    public Set<ProdutoResumoDTO> getProdutos() {
        return produtos;
    }

    public static CategoriaResponseDTO converterCategoria(Categoria categoria) {
        CategoriaResponseDTO responseObj = new CategoriaResponseDTO();
        responseObj.setId(categoria.getId());
        responseObj.setNome(categoria.getNome());
        responseObj.getProdutos().addAll(categoria.getProdutos().stream().<ProdutoResumoDTO>map(p -> ProdutoResumoDTO.converterProduto(p)).collect(Collectors.toList()));
        return responseObj;
    }

}
