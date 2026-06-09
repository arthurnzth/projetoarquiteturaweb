package com.arquiteturaweb.estoque.services;

import java.util.stream.Collectors;
import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arquiteturaweb.estoque.entities.Pedido;
import com.arquiteturaweb.estoque.entities.dto.pedido.PedidoResponseDTO;
import com.arquiteturaweb.estoque.entities.Produto;
import com.arquiteturaweb.estoque.entities.dto.produto.ProdutoResponseDTO;
import com.arquiteturaweb.estoque.repositories.FornecedorRepository;
import com.arquiteturaweb.estoque.repositories.PedidoRepository;


@Service
public class PedidoService {
    
    @Autowired
    private PedidoRepository pedidoRepositorio;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    //FUNÇÃO DE LISTAR TODOS OS PEDIDOS
    public List<PedidoResponseDTO> findAll() {

        List<Pedido> pedidos = pedidoRepositorio.findAll();

        return pedidos.stream().map(p -> PedidoResponseDTO.converterPedido(p)).collect(Collectors.toList());

    }

    //FUNÇÃO DE LISTAR 1 PEDIDO POR ID
    public PedidoResponseDTO findById(Long id) {

        Optional<Pedido> obj = pedidoRepositorio.findById(id);
        return PedidoResponseDTO.converterProduto(obj.orElseThrow(() -> new ResourceNotFoundException(id)));

    }

}
