package com.arquiteturaweb.estoque.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

import com.arquiteturaweb.estoque.entities.Categoria;
import com.arquiteturaweb.estoque.entities.dto.categoria.CategoriaRequestDTO;
import com.arquiteturaweb.estoque.entities.dto.categoria.CategoriaResponseDTO;
import com.arquiteturaweb.estoque.repositories.CategoriaRepository;
import com.arquiteturaweb.estoque.services.exceptions.DatabaseException;
import com.arquiteturaweb.estoque.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
class CategoriaServiceTest{
    @InjectMocks
    private CategoriaService service;
    @Mock
    private CategoriaRepository categoriaRepository;
    
    @Test
    void temQueRetornarListarCategorias(){
        List<Categoria> lista = Arrays.asList(new Categoria(1L,"Eletronicos"));
        when(categoriaRepository.findAll()).thenReturn(lista);
        List<CategoriaResponseDTO> resultado = service.findAll();
        assertEquals(1,resultado.size());
    }
    @Test
    void temQueRetornarCategoriaQuandoExiste(){
        Categoria categoria = new Categoria(1L,"Eletronicos");
        Mockito.when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));
        CategoriaResponseDTO resultado =service.findById(1L);
        assertEquals("Eletronicos",resultado.getNome());
    }
    @Test
    void TemQueLancarExcecaoQuandoIdNaoExiste(){
        Mockito.when(categoriaRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,() -> service.findById(99L));
    }
    @Test
    void deveInserirCategoria(){
        CategoriaRequestDTO dto = new CategoriaRequestDTO ("Roupas");
        Categoria categoria = new Categoria(1L,"Roupas");
        Mockito.when(categoriaRepository.save(any())).thenReturn(categoria);
        CategoriaResponseDTO resultado = service.insert(dto);
        assertEquals("Roupas",resultado.getNome());
    }
    @Test
    void temQueDeletarCategoria() {
        Mockito.doNothing().when(categoriaRepository).deleteById(1L);
        service.delete(1L);
        verify(categoriaRepository,times(1)).deleteById(1L);
    }
    @Test
    void temQueLancarErroAoExcluirIdInexistente(){
        Mockito.doThrow(EmptyResultDataAccessException.class).when(categoriaRepository).deleteById(99L);
        assertThrows(ResourceNotFoundException.class,() -> service.delete(99L));
    }
    @Test
    void temQueLancarDatabaseException(){
        Mockito.doThrow(DataIntegrityViolationException.class).when(categoriaRepository).deleteById(1L);
        assertThrows(DatabaseException.class,() -> service.delete(1L));
    }
    @Test
    void temQueAtualizarCategoria(){
        Categoria antiga = new Categoria(1L,"Velho");
        CategoriaRequestDTO nova = new CategoriaRequestDTO("Novo");
        Mockito.when(categoriaRepository.getReferenceById(1L)).thenReturn(antiga);
        Mockito.when(categoriaRepository.save(any())).thenReturn(antiga);
        CategoriaResponseDTO resultado = service.update(1L,nova);
        assertEquals("Novo",resultado.getNome());
    }
    @Test
    void temQueLancarErroAoAtualizarIdInexistente(){
        Mockito.when(categoriaRepository.getReferenceById(99L)).thenThrow(EntityNotFoundException.class);
        assertThrows(ResourceNotFoundException.class, () -> service.update(99L,new CategoriaRequestDTO("Teste")));
    }
}    