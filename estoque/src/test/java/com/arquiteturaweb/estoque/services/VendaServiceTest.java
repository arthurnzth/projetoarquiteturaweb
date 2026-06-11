package com.arquiteturaweb.estoque.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import com.arquiteturaweb.estoque.entities.Cliente;
import com.arquiteturaweb.estoque.entities.ItemVenda;
import com.arquiteturaweb.estoque.entities.Movimentacao;
import com.arquiteturaweb.estoque.entities.Produto;
import com.arquiteturaweb.estoque.entities.Usuario;
import com.arquiteturaweb.estoque.entities.Venda;
import com.arquiteturaweb.estoque.entities.dto.itemVenda.ItemVendaRequestDTO;
import com.arquiteturaweb.estoque.entities.dto.venda.VendaRequestDTO;
import com.arquiteturaweb.estoque.entities.dto.venda.VendaResponseDTO;
import com.arquiteturaweb.estoque.repositories.ClienteRepository;
import com.arquiteturaweb.estoque.repositories.ItemVendaRepository;
import com.arquiteturaweb.estoque.repositories.MovimentacaoRepository;
import com.arquiteturaweb.estoque.repositories.ProdutoRepository;
import com.arquiteturaweb.estoque.repositories.UsuarioRepository;
import com.arquiteturaweb.estoque.repositories.VendaRepository;
import com.arquiteturaweb.estoque.services.exceptions.DatabaseException;
import com.arquiteturaweb.estoque.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
class VendaServiceTest {

    @InjectMocks
    private VendaService vendaService;

    @Mock
    private VendaRepository vendaRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private ItemVendaRepository itemVendaRepository;

    @Mock
    private MovimentacaoRepository movimentacaoRepository;

    @Mock
    private EstoqueService estoqueService;

    @Test
    @DisplayName("Deve listar todas as vendas")
    void findAllDeveRetornarListaDeVendas() {
        Venda venda1 = criarVenda(1L, criarCliente(1L, "Cliente A"), criarUsuario(1L), "2026-06-01T10:00:00Z");
        Venda venda2 = criarVenda(2L, criarCliente(2L, "Cliente B"), criarUsuario(2L), "2026-06-02T10:00:00Z");

        when(vendaRepository.findAll()).thenReturn(Arrays.asList(venda1, venda2));

        List<VendaResponseDTO> resultado = vendaService.findAll();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(vendaRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve buscar venda por id quando existir")
    void findByIdDeveRetornarVendaQuandoExistir() {
        Long id = 1L;
        Venda venda = criarVenda(id, criarCliente(1L, "Cliente A"), criarUsuario(1L), "2026-06-01T10:00:00Z");

        when(vendaRepository.findById(id)).thenReturn(Optional.of(venda));

        VendaResponseDTO resultado = vendaService.findById(id);

        assertNotNull(resultado);
        verify(vendaRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException ao buscar venda inexistente")
    void findByIdDeveLancarExcecaoQuandoVendaNaoExistir() {
        Long id = 99L;

        when(vendaRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> vendaService.findById(id));
        verify(vendaRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Deve inserir venda com item e movimentação com sucesso")
    void insertDeveInserirVendaComItemEMovimentacaoComSucesso() {
        Cliente cliente = criarCliente(1L, "Cliente A");
        Usuario responsavel = criarUsuario(1L);
        Produto produto = criarProduto(1L, "Produto A", 50.0);
        ItemVendaRequestDTO itemRequest = new ItemVendaRequestDTO(2, produto.getId());
        VendaRequestDTO request = new VendaRequestDTO(cliente.getId(), "2026-06-01T10:00:00Z", responsavel.getId(), List.of(itemRequest), "Venda de teste");

        when(clienteRepository.getReferenceById(cliente.getId())).thenReturn(cliente);
        when(usuarioRepository.getReferenceById(responsavel.getId())).thenReturn(responsavel);
        when(produtoRepository.getReferenceById(produto.getId())).thenReturn(produto);
        when(vendaRepository.save(any(Venda.class))).thenAnswer(invocation -> {
            Venda vendaSalva = invocation.getArgument(0);
            if (vendaSalva.getIdVenda() == null) {
                vendaSalva.setIdVenda(1L);
            }
            return vendaSalva;
        });
        when(itemVendaRepository.save(any(ItemVenda.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(movimentacaoRepository.save(any(Movimentacao.class))).thenAnswer(invocation -> invocation.getArgument(0));

        VendaResponseDTO resultado = vendaService.insert(request);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals(1, resultado.getItens().size());

        ArgumentCaptor<ItemVenda> itemCaptor = ArgumentCaptor.forClass(ItemVenda.class);
        verify(itemVendaRepository, times(1)).save(itemCaptor.capture());
        assertSame(produto, itemCaptor.getValue().getProduto());
        assertEquals(2, itemCaptor.getValue().getQuantidade());
        assertEquals(100.0, itemCaptor.getValue().getSubTotal());

        ArgumentCaptor<Movimentacao> movimentacaoCaptor = ArgumentCaptor.forClass(Movimentacao.class);
        verify(movimentacaoRepository, times(1)).save(movimentacaoCaptor.capture());
        assertEquals("Venda de teste", movimentacaoCaptor.getValue().getObservacao());
        assertSame(responsavel, movimentacaoCaptor.getValue().getResponsavel());

        verify(vendaRepository, times(2)).save(any(Venda.class));
        verify(estoqueService, times(1)).remover(produto, 2);
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException quando houver erro ao inserir venda")
    void insertDeveLancarExcecaoQuandoNaoEncontrarDependencia() {
        VendaRequestDTO request = new VendaRequestDTO(99L, "2026-06-01T10:00:00Z", 1L, List.of(), "Venda inválida");

        when(clienteRepository.getReferenceById(99L)).thenThrow(new EntityNotFoundException());

        assertThrows(ResourceNotFoundException.class, () -> vendaService.insert(request));
        verify(clienteRepository, times(1)).getReferenceById(99L);
        verify(vendaRepository, never()).save(any(Venda.class));
    }

    @Test
    @DisplayName("Deve atualizar venda com sucesso")
    void updateDeveAtualizarVendaComSucesso() {
        Long id = 1L;
        Cliente clienteAntigo = criarCliente(1L, "Cliente Antigo");
        Cliente clienteNovo = criarCliente(2L, "Cliente Novo");
        Usuario responsavelAntigo = criarUsuario(1L);
        Usuario responsavelNovo = criarUsuario(2L);
        Venda vendaExistente = criarVenda(id, clienteAntigo, responsavelAntigo, "2026-06-01T10:00:00Z");
        VendaRequestDTO request = new VendaRequestDTO(clienteNovo.getId(), "2026-06-05T15:30:00Z", responsavelNovo.getId(), List.of(), "Atualização de venda");

        when(vendaRepository.getReferenceById(id)).thenReturn(vendaExistente);
        when(clienteRepository.getReferenceById(clienteNovo.getId())).thenReturn(clienteNovo);
        when(usuarioRepository.getReferenceById(responsavelNovo.getId())).thenReturn(responsavelNovo);
        when(vendaRepository.save(any(Venda.class))).thenAnswer(invocation -> invocation.getArgument(0));

        VendaResponseDTO resultado = vendaService.update(id, request);

        assertNotNull(resultado);
        assertSame(clienteNovo, vendaExistente.getClienteVenda());
        assertSame(responsavelNovo, vendaExistente.getResponsavelVenda());
        assertEquals(Instant.parse("2026-06-05T15:30:00Z"), vendaExistente.getDataVenda());
        verify(vendaRepository, times(1)).getReferenceById(id);
        verify(vendaRepository, times(1)).save(vendaExistente);
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException ao atualizar venda inexistente")
    void updateDeveLancarExcecaoQuandoVendaNaoExistir() {
        Long id = 99L;
        VendaRequestDTO request = new VendaRequestDTO(1L, "2026-06-01T10:00:00Z", 1L, List.of(), "Venda inexistente");

        when(vendaRepository.getReferenceById(id)).thenThrow(new EntityNotFoundException());

        assertThrows(ResourceNotFoundException.class, () -> vendaService.update(id, request));
        verify(vendaRepository, times(1)).getReferenceById(id);
        verify(vendaRepository, never()).save(any(Venda.class));
    }

    @Test
    @DisplayName("Deve deletar venda com sucesso")
    void deleteDeveRemoverVendaComSucesso() {
        Long id = 1L;

        doNothing().when(vendaRepository).deleteById(id);

        assertDoesNotThrow(() -> vendaService.delete(id));
        verify(vendaRepository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException ao deletar venda inexistente")
    void deleteDeveLancarExcecaoQuandoVendaNaoExistir() {
        Long id = 99L;

        doThrow(new EmptyResultDataAccessException(1)).when(vendaRepository).deleteById(id);

        assertThrows(ResourceNotFoundException.class, () -> vendaService.delete(id));
        verify(vendaRepository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Deve lançar DatabaseException quando houver erro de integridade ao deletar")
    void deleteDeveLancarDatabaseExceptionQuandoVendaEstiverRelacionada() {
        Long id = 1L;

        doThrow(new DataIntegrityViolationException("Venda relacionada a outra entidade"))
                .when(vendaRepository).deleteById(id);

        assertThrows(DatabaseException.class, () -> vendaService.delete(id));
        verify(vendaRepository, times(1)).deleteById(id);
    }

    private Venda criarVenda(Long id, Cliente cliente, Usuario responsavel, String data) {
        return new Venda(id, cliente, Instant.parse(data), null, responsavel);
    }

    private Cliente criarCliente(Long id, String nome) {
        return new Cliente(id, nome, "12.345.678/0001-90", "cliente@email.com");
    }

    private Usuario criarUsuario(Long id) {
        Usuario usuario = new Usuario(id, "Usuário " + id, "usuario" + id + "@email.com", "Vendedor", true);
        usuario.setCargo("Vendedor");
        return usuario;
    }

    private Produto criarProduto(Long id, String nome, Double preco) {
        return new Produto(id, nome, "Produto usado no teste", preco, null);
    }
}
