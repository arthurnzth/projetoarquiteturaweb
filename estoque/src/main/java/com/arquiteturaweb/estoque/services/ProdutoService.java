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
import com.arquiteturaweb.estoque.entities.dto.ProdutoRequestDTO;
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

    private ProdutoProducer produtoProducer;

    public List<Produto> findAll() {

        return produtoRepository.findAll();

    }

    public Produto findById(Long id) {

        Optional<Produto> obj = produtoRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));

    }

    public Produto insert(ProdutoRequestDTO obj) {

        try {
            List<Categoria> categorias = categoriaRepository.findAllById(obj.getCategoriasId());
    
            Fornecedor fornecedor = fornecedorRepository.findById(obj.getFornecedorId()).orElseThrow(() -> new ResourceNotFoundException(obj.getFornecedorId()));
    
            Produto produto = new Produto(obj.getId(), obj.getNome(), obj.getDescricao(), obj.getPreco(), fornecedor);
            produto.getCategorias().addAll(categorias);

            produtoRepository.save(produto);

            CadastroProdutoEvent event = new CadastroProdutoEvent(produto.getId());
            produtoProducer.enviarCadastroProdutoEvent(event);
            
            return produto;

        } catch (RuntimeException e) {
            throw new ResourceNotFoundException(obj.getCategoriasId(), obj.getFornecedorId());

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

    public Produto update(Long id, ProdutoRequestDTO obj) {

        try {
            Produto entity = produtoRepository.getReferenceById(id);
            updateData(entity, obj);
            return produtoRepository.save(entity);

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
            throw new ResourceNotFoundException(obj.getCategoriasId(), obj.getFornecedorId());

        }

    }

}
