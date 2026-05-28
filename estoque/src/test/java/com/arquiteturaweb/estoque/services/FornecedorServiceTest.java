package com.arquiteturaweb.estoque.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import com.arquiteturaweb.estoque.entities.Fornecedor;
import com.arquiteturaweb.estoque.entities.dto.fornecedor.FornecedorRequestDTO;
import com.arquiteturaweb.estoque.entities.dto.fornecedor.FornecedorResponseDTO;
import com.arquiteturaweb.estoque.repositories.FornecedorRepository;
import com.arquiteturaweb.estoque.services.exceptions.DatabaseException;
import com.arquiteturaweb.estoque.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
class FornecedorServiceTest {

    @Mock
    private FornecedorRepository fornecedorRepository;

    private FornecedorService fornecedorService;

    @BeforeEach
    void setUp() {
        fornecedorService = new FornecedorService(fornecedorRepository);
    }

    @Test
    @DisplayName("Deve listar todos os fornecedores")
    void findAllDeveRetornarListaDeFornecedores() {
        Fornecedor fornecedor1 = new Fornecedor(1L, "Fornecedor A", "Rua A", "11999990000");
        Fornecedor fornecedor2 = new Fornecedor(2L, "Fornecedor B", "Rua B", "11888880000");

        when(fornecedorRepository.findAll()).thenReturn(Arrays.asList(fornecedor1, fornecedor2));

        List<FornecedorResponseDTO> resultado = fornecedorService.findAll();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(fornecedorRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve buscar fornecedor por id quando existir")
    void findByIdDeveRetornarFornecedorQuandoExistir() {
        Long id = 1L;
        Fornecedor fornecedor = new Fornecedor(id, "Fornecedor A", "Rua A", "11999990000");

        when(fornecedorRepository.findById(id)).thenReturn(Optional.of(fornecedor));

        FornecedorResponseDTO resultado = fornecedorService.findById(id);

        assertNotNull(resultado);
        verify(fornecedorRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException ao buscar fornecedor inexistente")
    void findByIdDeveLancarExcecaoQuandoFornecedorNaoExistir() {
        Long id = 99L;

        when(fornecedorRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> fornecedorService.findById(id));
        verify(fornecedorRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Deve salvar fornecedor com sucesso")
    void saveDeveSalvarFornecedorComSucesso() {
        FornecedorRequestDTO request = mock(FornecedorRequestDTO.class);
        when(request.getNome()).thenReturn("Fornecedor A");
        when(request.getEndereco()).thenReturn("Rua A");
        when(request.getTelefone()).thenReturn("11999990000");

        when(fornecedorRepository.save(any(Fornecedor.class))).thenAnswer(invocation -> {
            Fornecedor fornecedorSalvo = invocation.getArgument(0);
            fornecedorSalvo.setIdFornecedor(1L);
            return fornecedorSalvo;
        });

        FornecedorResponseDTO resultado = fornecedorService.save(request);

        assertNotNull(resultado);

        ArgumentCaptor<Fornecedor> captor = ArgumentCaptor.forClass(Fornecedor.class);
        verify(fornecedorRepository, times(1)).save(captor.capture());

        Fornecedor fornecedorEnviadoParaSalvar = captor.getValue();
        assertEquals("Fornecedor A", fornecedorEnviadoParaSalvar.getNomeFornecedor());
        assertEquals("Rua A", fornecedorEnviadoParaSalvar.getEnderecoFornecedor());
        assertEquals("11999990000", fornecedorEnviadoParaSalvar.getTelefoneFornecedor());
    }

    @Test
    @DisplayName("Deve lançar RuntimeException quando ocorrer erro de integridade ao salvar")
    void saveDeveLancarRuntimeExceptionQuandoDadosForemInvalidos() {
        FornecedorRequestDTO request = mock(FornecedorRequestDTO.class);
        when(request.getNome()).thenReturn("Fornecedor A");
        when(request.getEndereco()).thenReturn("Rua A");
        when(request.getTelefone()).thenReturn("11999990000");

        when(fornecedorRepository.save(any(Fornecedor.class)))
                .thenThrow(new DataIntegrityViolationException("Erro de integridade"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> fornecedorService.save(request));

        assertEquals("Dados inválidos para salvar fornecedor", exception.getMessage());
        verify(fornecedorRepository, times(1)).save(any(Fornecedor.class));
    }

    @Test
    @DisplayName("Deve atualizar fornecedor com sucesso")
    void updateDeveAtualizarFornecedorComSucesso() {
        Long id = 1L;
        Fornecedor fornecedorExistente = new Fornecedor(id, "Nome antigo", "Endereço antigo", "11111111111");

        FornecedorRequestDTO request = mock(FornecedorRequestDTO.class);
        when(request.getNome()).thenReturn("Nome novo");
        when(request.getEndereco()).thenReturn("Endereço novo");
        when(request.getTelefone()).thenReturn("22222222222");

        when(fornecedorRepository.getReferenceById(id)).thenReturn(fornecedorExistente);
        when(fornecedorRepository.save(any(Fornecedor.class))).thenAnswer(invocation -> invocation.getArgument(0));

        FornecedorResponseDTO resultado = fornecedorService.update(id, request);

        assertNotNull(resultado);
        assertEquals("Nome novo", fornecedorExistente.getNomeFornecedor());
        assertEquals("Endereço novo", fornecedorExistente.getEnderecoFornecedor());
        assertEquals("22222222222", fornecedorExistente.getTelefoneFornecedor());
        verify(fornecedorRepository, times(1)).getReferenceById(id);
        verify(fornecedorRepository, times(1)).save(fornecedorExistente);
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException ao atualizar fornecedor inexistente")
    void updateDeveLancarExcecaoQuandoFornecedorNaoExistir() {
        Long id = 99L;
        FornecedorRequestDTO request = mock(FornecedorRequestDTO.class);

        when(fornecedorRepository.getReferenceById(id)).thenThrow(new EntityNotFoundException());

        assertThrows(ResourceNotFoundException.class, () -> fornecedorService.update(id, request));
        verify(fornecedorRepository, times(1)).getReferenceById(id);
        verify(fornecedorRepository, never()).save(any(Fornecedor.class));
    }

    @Test
    @DisplayName("Deve deletar fornecedor com sucesso")
    void deleteDeveRemoverFornecedorComSucesso() {
        Long id = 1L;

        doNothing().when(fornecedorRepository).deleteById(id);

        assertDoesNotThrow(() -> fornecedorService.delete(id));
        verify(fornecedorRepository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException ao deletar fornecedor inexistente")
    void deleteDeveLancarExcecaoQuandoFornecedorNaoExistir() {
        Long id = 99L;

        doThrow(new EmptyResultDataAccessException(1)).when(fornecedorRepository).deleteById(id);

        assertThrows(ResourceNotFoundException.class, () -> fornecedorService.delete(id));
        verify(fornecedorRepository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Deve lançar DatabaseException quando houver erro de integridade ao deletar")
    void deleteDeveLancarDatabaseExceptionQuandoFornecedorEstiverRelacionado() {
        Long id = 1L;

        doThrow(new DataIntegrityViolationException("Fornecedor relacionado a produto"))
                .when(fornecedorRepository).deleteById(id);

        assertThrows(DatabaseException.class, () -> fornecedorService.delete(id));
        verify(fornecedorRepository, times(1)).deleteById(id);
    }
}
