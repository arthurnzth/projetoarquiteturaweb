package com.arquiteturaweb.estoque.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.arquiteturaweb.estoque.entities.Categoria;
import com.arquiteturaweb.estoque.entities.Fornecedor;
import com.arquiteturaweb.estoque.entities.Produto;
import com.arquiteturaweb.estoque.entities.dto.produto.ProdutoRequestDTO;
import com.arquiteturaweb.estoque.entities.dto.produto.ProdutoResponseDTO;
import com.arquiteturaweb.estoque.events.CadastroProdutoEvent;
import com.arquiteturaweb.estoque.producers.ProdutoProducer;
import com.arquiteturaweb.estoque.repositories.CategoriaRepository;
import com.arquiteturaweb.estoque.repositories.FornecedorRepository;
import com.arquiteturaweb.estoque.repositories.ProdutoRepository;
import com.arquiteturaweb.estoque.services.exceptions.DatabaseException;
import com.arquiteturaweb.estoque.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private ProdutoProducer produtoProducer;

    public List<ProdutoResponseDTO> findAll() {

        List<Produto> produtos = produtoRepository.findAll();

        return produtos.stream().map(p -> ProdutoResponseDTO.converterProduto(p)).collect(Collectors.toList());

    }

    public ProdutoResponseDTO findById(Long id) {

        Optional<Produto> obj = produtoRepository.findById(id);
        return ProdutoResponseDTO.converterProduto(obj.orElseThrow(() -> new ResourceNotFoundException(id)));

    }

    public ProdutoResponseDTO insert(ProdutoRequestDTO requestObj) {

        try {
            List<Categoria> categorias = categoriaRepository.findAllById(requestObj.getCategoriasId());
    
            Fornecedor fornecedor = fornecedorRepository.findById(requestObj.getFornecedorId()).orElseThrow(() -> new ResourceNotFoundException(requestObj.getFornecedorId()));
    
            Produto produto = new Produto(null, requestObj.getNome(), requestObj.getDescricao(), requestObj.getPreco(), fornecedor);
            produto.getCategorias().addAll(categorias);

            Produto produtoSalvo = produtoRepository.save(produto);

            Thread.sleep(1500L);

            CadastroProdutoEvent event = new CadastroProdutoEvent(produtoSalvo.getId());
            produtoProducer.enviarCadastroProdutoEvent(event);

            ProdutoResponseDTO responseObj = ProdutoResponseDTO.converterProduto(produtoSalvo);
            
            return responseObj;

        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new ResourceNotFoundException(requestObj.getFornecedorId());

        } catch (InterruptedException e) {
            throw new DatabaseException(e.getMessage());
        }

    }

    public void delete(Long id) {

        try {
            produtoRepository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);

        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());

        }

    }

    public ProdutoResponseDTO update(Long id, ProdutoRequestDTO obj) {

        try {
            Produto entity = produtoRepository.getReferenceById(id);
            updateData(entity, obj);
            return ProdutoResponseDTO.converterProduto(produtoRepository.save(entity));

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);

        }

    }

    private void updateData(Produto entity, ProdutoRequestDTO obj) {

        try {
            entity.setNome(obj.getNome());
            entity.setDescricao(obj.getDescricao());
            entity.setPreco(obj.getPreco());
            entity.getCategorias().clear();
            entity.getCategorias().addAll(obj.getCategoriasId().stream().map(id -> categoriaRepository.getReferenceById(id)).collect(Collectors.toList()));
            entity.setFornecedor(fornecedorRepository.getReferenceById(obj.getFornecedorId()));

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(obj.getFornecedorId());

        }

    }

}
