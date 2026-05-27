package com.arquiteturaweb.estoque.entities.dto.fornecedor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.arquiteturaweb.estoque.entities.Fornecedor;
import com.arquiteturaweb.estoque.entities.dto.produto.ProdutoResumoDTO;

public class FornecedorResponseDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private String endereco;
    private String telefone;
    private Set<ProdutoResumoDTO> produtos = new HashSet<>();

    public FornecedorResponseDTO() {

    }

    public FornecedorResponseDTO(Long id, String nome, String endereco, String telefone) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
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
    
    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Set<ProdutoResumoDTO> getProdutos() {
        return produtos;
    }

    public static FornecedorResponseDTO converterFornecedor(Fornecedor fornecedor) {
        FornecedorResponseDTO responseObj = new FornecedorResponseDTO();
        responseObj.setId(fornecedor.getIdFornecedor());
        responseObj.setNome(fornecedor.getNomeFornecedor());
        responseObj.setEndereco(fornecedor.getEnderecoFornecedor());
        responseObj.setTelefone(fornecedor.getTelefoneFornecedor());
        responseObj.getProdutos().addAll(fornecedor.getProdutos().stream().<ProdutoResumoDTO>map(p -> ProdutoResumoDTO.converterProduto(p)).collect(Collectors.toList()));
        return responseObj;
    }

}
