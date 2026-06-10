package com.arquiteturaweb.estoque.services;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import com.arquiteturaweb.estoque.entities.Cliente;
import com.arquiteturaweb.estoque.entities.dto.cliente.ClienteRequestDTO;
import com.arquiteturaweb.estoque.entities.dto.cliente.ClienteResponseDTO;
import com.arquiteturaweb.estoque.repositories.ClienteRepository;
import com.arquiteturaweb.estoque.services.exceptions.DatabaseException;
import com.arquiteturaweb.estoque.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @InjectMocks
    private ClienteService service;

    @Mock
    private ClienteRepository clienteRepository;



@Test
void deveRetornarListaClientes() {
 System.out.println("TESTE FindALL");
    Cliente cliente =
            new Cliente(1L,
                    "Empresa Teste",
                    "123456789",
                    "31999999999");

    when(clienteRepository.findAll())
            .thenReturn(List.of(cliente));

    List<ClienteResponseDTO> resultado =
            service.findAll();

    assertEquals(1, resultado.size());
}

@Test
void deveRetornarClienteQuandoExiste() {
 System.out.println("findById sucesso");
    Cliente cliente =
            new Cliente(1L,
                    "Empresa Teste",
                    "123456789",
                    "31999999999");

    when(clienteRepository.findById(1L))
            .thenReturn(Optional.of(cliente));

    ClienteResponseDTO resultado =
            service.findById(1L);

    assertEquals(1L, resultado.getId());
}

@Test
void deveLancarExcecaoQuandoClienteNaoExiste() {
 System.out.println("findById erro");
    when(clienteRepository.findById(99L))
            .thenReturn(Optional.empty());

    assertThrows(
            ResourceNotFoundException.class,
            () -> service.findById(99L)
    );
}

@Test
void deveInserirCliente() {
  System.out.println("TESTE DE INSERT");
    ClienteRequestDTO dto =
            new ClienteRequestDTO(
                    "Empresa Teste",
                    "123456789",
                    "31999999999");

    Cliente cliente =
            new Cliente(1L,
                    dto.getNome(),
                    dto.getCnpj(),
                    dto.getContato());

    when(clienteRepository.save(any()))
            .thenReturn(cliente);

    ClienteResponseDTO resultado =
            service.insert(dto);

    assertEquals("Empresa Teste",
            resultado.getNome());
}

@Test
void deveExcluirCliente() {
  System.out.println("TESTE DE DELETE SUCESSO");
    Mockito.doNothing()
            .when(clienteRepository)
            .deleteById(1L);

    service.delete(1L);
}

@Test
void deveLancarExcecaoAoExcluirClienteInexistente() {
  System.out.println("DELETE CLIENTE INEXISTENTE");
    Mockito.doThrow(
            new EmptyResultDataAccessException(1))
            .when(clienteRepository)
            .deleteById(99L);

    assertThrows(
            ResourceNotFoundException.class,
            () -> service.delete(99L)
    );
}
@Test
void deveAtualizarCliente() {
    System.out.println("UPDATE SUCESSO");
    Cliente cliente =
            new Cliente(1L,
                    "Antigo",
                    "111",
                    "111");

    ClienteRequestDTO dto =
            new ClienteRequestDTO(
                    "Novo",
                    "222",
                    "222");

    when(clienteRepository.getReferenceById(1L))
            .thenReturn(cliente);

    when(clienteRepository.save(any()))
            .thenReturn(cliente);

    ClienteResponseDTO resultado =
            service.update(1L, dto);

    assertEquals("Novo",
            resultado.getNome());
}

@Test
void deveLancarExcecaoAoAtualizarClienteInexistente() {
    System.out.println("UPDATE CLIENTE INEXISTENTE");
    when(clienteRepository.getReferenceById(99L))
            .thenThrow(EntityNotFoundException.class);

    ClienteRequestDTO dto =
            new ClienteRequestDTO(
                    "Novo",
                    "222",
                    "222");

    assertThrows(
            ResourceNotFoundException.class,
            () -> service.update(99L, dto)
    );
}
}