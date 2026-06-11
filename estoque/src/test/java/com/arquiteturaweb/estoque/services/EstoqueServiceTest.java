package com.arquiteturaweb.estoque.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.arquiteturaweb.estoque.entities.Estoque;
import com.arquiteturaweb.estoque.entities.Produto;
import com.arquiteturaweb.estoque.entities.dto.estoque.EstoqueResponseDTO;
import com.arquiteturaweb.estoque.repositories.EstoqueRepository;
import com.arquiteturaweb.estoque.repositories.ProdutoRepository;
import com.arquiteturaweb.estoque.services.exceptions.ResourceNotFoundException;

@ExtendWith(MockitoExtension.class)
class EstoqueServiceTest {

    @InjectMocks
    private EstoqueService service;

    @Mock
    private EstoqueRepository estoqueRepository;

    @Mock
    private ProdutoRepository produtoRepository;

    @Test
    void temQueRetornarListarEstoques() {

        Produto produto = new Produto();
        produto.setId(1L);

        Estoque estoque = new Estoque(1L, produto);

        List<Estoque> lista = Arrays.asList(estoque);

        when(estoqueRepository.findAll()).thenReturn(lista);

        List<EstoqueResponseDTO> resultado = service.findAll();

        assertEquals(1, resultado.size());
    }

    @Test
    void temQueRetornarEstoqueQuandoExiste() {

        Produto produto = new Produto();
        produto.setId(1L);

        Estoque estoque = new Estoque(1L, produto);

        when(estoqueRepository.findById(1L))
                .thenReturn(Optional.of(estoque));

        EstoqueResponseDTO resultado = service.findById(1L);

        assertEquals(1L, resultado.getId());
    }

    @Test
    void temQueLancarExcecaoQuandoIdNaoExiste() {

        when(estoqueRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> service.findById(99L));
    }

    @Test
    void deveCriarEstoqueInicial() {

        Produto produto = new Produto();
        produto.setId(1L);

        Mockito.when(produtoRepository.findById(1L))
                .thenReturn(Optional.of(produto));

        Mockito.when(estoqueRepository.save(any()))
                .thenAnswer(invocation -> invocation.getArgument(0));

        service.criarEstoqueInicial(1L);

        assertEquals(0, produto.getEstoque().getQuantidade());
    }
}
