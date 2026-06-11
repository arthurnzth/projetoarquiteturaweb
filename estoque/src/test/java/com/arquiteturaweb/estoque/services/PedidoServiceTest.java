package com.arquiteturaweb.estoque.services;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import com.arquiteturaweb.estoque.entities.Fornecedor;
import com.arquiteturaweb.estoque.entities.Pedido;
import com.arquiteturaweb.estoque.entities.Usuario;
import com.arquiteturaweb.estoque.entities.dto.pedido.PedidoResponseDTO;

import com.arquiteturaweb.estoque.repositories.PedidoRepository;
import com.arquiteturaweb.estoque.repositories.FornecedorRepository;
import com.arquiteturaweb.estoque.repositories.UsuarioRepository;
import com.arquiteturaweb.estoque.repositories.ProdutoRepository;
import com.arquiteturaweb.estoque.repositories.ItemPedidoRepository;
import com.arquiteturaweb.estoque.repositories.MovimentacaoRepository;

import com.arquiteturaweb.estoque.services.exceptions.DatabaseException;
import com.arquiteturaweb.estoque.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
public class PedidoServiceTest {
     @InjectMocks
    private PedidoService service;

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private FornecedorRepository fornecedorRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private ItemPedidoRepository itemPedidoRepository;

    @Mock
    private MovimentacaoRepository movimentacaoRepository;

    @Mock
    private EstoqueService estoqueService;

@Test
    void temQueRetornarListarPedidos() {
    System.out.println("TESTE RODOU");
    Fornecedor fornecedor = new Fornecedor();
    fornecedor.setIdFornecedor(1L);
    fornecedor.setNomeFornecedor("Fornecedor Teste");

    Usuario usuario = new Usuario();
    usuario.setId(1L);
    usuario.setNome("Eduarda");
    usuario.setCargo("Admin");
    usuario.setAtivo(true);

    Pedido pedido = new Pedido(
            1L,
            fornecedor,
            Instant.now(),
            null,
            usuario);

    when(pedidoRepository.findAll())
            .thenReturn(List.of(pedido));

    List<PedidoResponseDTO> resultado =
            service.findAll();

    assertEquals(1, resultado.size());
}
    @Test
    void temQueRetornarPedidoQuandoExiste() {
        System.out.println("TESTE 2");
    Fornecedor fornecedor = new Fornecedor();
    fornecedor.setIdFornecedor(1L);
    fornecedor.setNomeFornecedor("Fornecedor Teste");

    Usuario usuario = new Usuario();
    usuario.setId(1L);
    usuario.setNome("Eduarda");
    usuario.setCargo("Admin");
    usuario.setAtivo(true);

    Pedido pedido = new Pedido(
            1L,
            fornecedor,
            Instant.now(),
            null,
            usuario);

    when(pedidoRepository.findById(1L))
            .thenReturn(Optional.of(pedido));

    PedidoResponseDTO resultado =
            service.findById(1L);

    assertEquals(1L, resultado.getId());
}
    @Test
void temQueLancarExcecaoQuandoIdNaoExiste() {

    when(pedidoRepository.findById(99L))
            .thenReturn(Optional.empty());

    assertThrows(
            ResourceNotFoundException.class,
            () -> service.findById(99L));
}

@Test
void temQueDeletarPedido() {

    Mockito.doNothing()
            .when(pedidoRepository)
            .deleteById(1L);

    service.delete(1L);

    verify(pedidoRepository, times(1))
            .deleteById(1L);
}

@Test
void temQueLancarErroAoExcluirIdInexistente() {

    Mockito.doThrow(
            EmptyResultDataAccessException.class)
            .when(pedidoRepository)
            .deleteById(99L);

    assertThrows(
            ResourceNotFoundException.class,
            () -> service.delete(99L));
}

@Test
void temQueLancarDatabaseException() {

    Mockito.doThrow(
            DataIntegrityViolationException.class)
            .when(pedidoRepository)
            .deleteById(1L);

    assertThrows(
            DatabaseException.class,
            () -> service.delete(1L));
}
}
