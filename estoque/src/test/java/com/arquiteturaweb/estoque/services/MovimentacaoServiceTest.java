package com.arquiteturaweb.estoque.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.arquiteturaweb.estoque.entities.Cliente;
import com.arquiteturaweb.estoque.entities.Movimentacao;
import com.arquiteturaweb.estoque.entities.Usuario;
import com.arquiteturaweb.estoque.entities.Venda;
import com.arquiteturaweb.estoque.entities.dto.movimentacao.MovimentacaoRequestDTO;
import com.arquiteturaweb.estoque.entities.dto.movimentacao.MovimentacaoResponseDTO;
import com.arquiteturaweb.estoque.repositories.MovimentacaoRepository;
import com.arquiteturaweb.estoque.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
class MovimentacaoServiceTest {

    @InjectMocks
    private MovimentacaoService movimentacaoService;

    @Mock
    private MovimentacaoRepository movimentacaoRepository;

    @Test
    @DisplayName("Deve listar todas as movimentações")
    void findAllDeveRetornarListaDeMovimentacoes() {
        Movimentacao movimentacao1 = criarMovimentacaoSaida(1L, "Venda realizada");
        Movimentacao movimentacao2 = criarMovimentacaoSaida(2L, "Venda entregue");

        when(movimentacaoRepository.findAll()).thenReturn(Arrays.asList(movimentacao1, movimentacao2));

        List<MovimentacaoResponseDTO> resultado = movimentacaoService.findAll();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals(1L, resultado.get(0).getId());
        assertEquals("SAIDA", resultado.get(0).getTipo());
        assertEquals("Venda realizada", resultado.get(0).getObservacao());
        assertNotNull(resultado.get(0).getResponsavel());
        assertNotNull(resultado.get(0).getEntidade());

        verify(movimentacaoRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve buscar movimentação por id quando existir")
    void findByIdDeveRetornarMovimentacaoQuandoExistir() {
        Long id = 1L;
        Movimentacao movimentacao = criarMovimentacaoSaida(id, "Venda realizada");

        when(movimentacaoRepository.findById(id)).thenReturn(Optional.of(movimentacao));

        MovimentacaoResponseDTO resultado = movimentacaoService.findById(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        assertEquals("SAIDA", resultado.getTipo());
        assertEquals("Venda realizada", resultado.getObservacao());
        assertEquals("Usuário Responsável", resultado.getResponsavel().getNome());
        assertEquals("ativo", resultado.getResponsavel().getAtivo());
        assertNotNull(resultado.getEntidade());

        verify(movimentacaoRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException ao buscar movimentação inexistente")
    void findByIdDeveLancarExcecaoQuandoMovimentacaoNaoExistir() {
        Long id = 99L;

        when(movimentacaoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> movimentacaoService.findById(id));
        verify(movimentacaoRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Deve atualizar somente a observação da movimentação com sucesso")
    void updateDeveAtualizarObservacaoDaMovimentacaoComSucesso() {
        Long id = 1L;
        Movimentacao movimentacaoExistente = criarMovimentacaoSaida(id, "Observação antiga");
        MovimentacaoRequestDTO request = new MovimentacaoRequestDTO("Observação atualizada");

        when(movimentacaoRepository.getReferenceById(id)).thenReturn(movimentacaoExistente);
        when(movimentacaoRepository.save(any(Movimentacao.class))).thenAnswer(invocation -> invocation.getArgument(0));

        MovimentacaoResponseDTO resultado = movimentacaoService.update(id, request);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        assertEquals("SAIDA", resultado.getTipo());
        assertEquals("Observação atualizada", resultado.getObservacao());
        assertEquals("Observação atualizada", movimentacaoExistente.getObservacao());

        verify(movimentacaoRepository, times(1)).getReferenceById(id);
        verify(movimentacaoRepository, times(1)).save(movimentacaoExistente);
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException ao atualizar movimentação inexistente")
    void updateDeveLancarExcecaoQuandoMovimentacaoNaoExistir() {
        Long id = 99L;
        MovimentacaoRequestDTO request = new MovimentacaoRequestDTO("Observação atualizada");

        when(movimentacaoRepository.getReferenceById(id)).thenThrow(new EntityNotFoundException());

        assertThrows(ResourceNotFoundException.class, () -> movimentacaoService.update(id, request));
        verify(movimentacaoRepository, times(1)).getReferenceById(id);
        verify(movimentacaoRepository, never()).save(any(Movimentacao.class));
    }

    private Movimentacao criarMovimentacaoSaida(Long id, String observacao) {
        Usuario responsavel = criarUsuarioResponsavel();
        Cliente cliente = new Cliente(1L, "Cliente Teste", "12.345.678/0001-90", "cliente@email.com");
        Venda venda = new Venda(1L, cliente, Instant.parse("2026-06-01T10:00:00Z"), null, responsavel);

        return new Movimentacao(id, Instant.parse("2026-06-01T10:05:00Z"), venda, responsavel, observacao);
    }

    private Usuario criarUsuarioResponsavel() {
        Usuario usuario = new Usuario(1L, "Usuário Responsável", "responsavel@email.com", "Vendedor", true);
        usuario.setCargo("Vendedor");
        return usuario;
    }
}
