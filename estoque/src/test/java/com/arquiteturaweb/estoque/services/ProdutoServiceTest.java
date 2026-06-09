package com.arquiteturaweb.estoque.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import com.arquiteturaweb.estoque.entities.Categoria;
import com.arquiteturaweb.estoque.entities.Fornecedor;
import com.arquiteturaweb.estoque.entities.Produto;
import com.arquiteturaweb.estoque.entities.dto.produto.ProdutoRequestDTO;
import com.arquiteturaweb.estoque.entities.dto.produto.ProdutoResponseDTO;
import com.arquiteturaweb.estoque.producers.ProdutoProducer;
import com.arquiteturaweb.estoque.repositories.CategoriaRepository;
import com.arquiteturaweb.estoque.repositories.FornecedorRepository;
import com.arquiteturaweb.estoque.repositories.ProdutoRepository;
import com.arquiteturaweb.estoque.services.exceptions.ResourceNotFoundException;


@ExtendWith(MockitoExtension.class) class ProdutoServiceTest {
    
    @InjectMocks
    private ProdutoService service;
    
    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private CategoriaRepository categoriaRepository; 

    @Mock
    private FornecedorRepository fornecedorRepository;
    
    @Mock
    private ProdutoProducer produtoProducer;
    
    @Test
    void temQueRetornarListaProdutos(){
        Fornecedor fornecedor = new Fornecedor (1L,"Fornecedor A","Rua S", "3199999999");
        Produto produto = new Produto (1L,"Notebook","Dell",3000.0,fornecedor);
        when(produtoRepository.findAll()).thenReturn(Arrays.asList(produto));
        List<ProdutoResponseDTO> resultado = service.findAll();
        assertEquals(1, resultado.size());
        assertEquals("Notebook",resultado.get(0).getNome());
    }
    @Test
    void temQueRetornarQuandoProdutoExiste(){
        Fornecedor fornecedor = new Fornecedor (1L,"Fornecedor A","Rua S", "3199999999");
        Produto produto = new Produto (1L,"Notebook","Dell",3000.0,fornecedor);
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
        ProdutoResponseDTO resultado = service.findById(1L);
        assertEquals("Notebook", resultado.getNome());
    }
    @Test
    void temQueLancarErroQuandoProdutoNaoExiste(){
        when(produtoRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,()-> service.findById(99L));    
    }
    @Test
    void temQueDeletarProduto(){
        doNothing().when(produtoRepository).deleteById(1L);
        service.delete(1L);
        verify(produtoRepository,times(1)).deleteById(1L);
    }
    @Test
    void temQueInserirProduto(){
        Categoria categoria = new Categoria(1L,"Eletronicos");
        Fornecedor fornecedor = new Fornecedor (1L,"Fornecedor A","Rua S", "3199999999");
        Produto produto =new Produto( 1L,"Notebook","Dell",3000.0, fornecedor);
        
        produto.getCategorias().add(categoria);
        ProdutoRequestDTO dto = new ProdutoRequestDTO();
        
        dto.setNome("Notebook");
        dto.setDescricao("Dell");
        dto.setPreco(3000.0);
        dto.setFornecedorId(1L);
        dto.setCategoriasId(Set.of(1L));
        
        when(categoriaRepository.findAllById(any())).thenReturn(List.of(categoria));
        when(fornecedorRepository.findById(1L)).thenReturn(Optional.of(fornecedor));
        when(produtoRepository.save(any())).thenReturn(produto);
        
        ProdutoResponseDTO resultado = service.insert(dto);
        
        assertEquals("Notebook",resultado.getNome());
        verify(produtoProducer, times(1)).enviarCadastroProdutoEvent(any());
    }
    @Test
    void temQueAtualizarProduto(){
        
        Fornecedor fornecedor = new Fornecedor (1L,"Fornecedor A","Rua S", "3199999999");
        Produto produto = new Produto(1L,"Velho","Descricao",1000.0,fornecedor);
        ProdutoRequestDTO dto = new ProdutoRequestDTO();
        
        dto.setNome("Novo");
        dto.setDescricao("Nova");
        dto.setPreco(2000.0);
        dto.setFornecedorId(1L);
        dto.setCategoriasId(Set.of());
        
        when(produtoRepository.getReferenceById(1L)).thenReturn(produto);
        when(fornecedorRepository.getReferenceById(1L)).thenReturn(fornecedor);
        when(produtoRepository.save(any())).thenReturn(produto);
        ProdutoResponseDTO resultado = service.update(1L, dto);
        assertEquals("Novo",resultado.getNome());
    }
}