package com.arquiteturaweb.estoque.services;

import java.util.stream.Collectors;
import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arquiteturaweb.estoque.entities.Categoria;
import com.arquiteturaweb.estoque.entities.Fornecedor;
import com.arquiteturaweb.estoque.entities.Pedido;
import com.arquiteturaweb.estoque.entities.dto.pedido.PedidoRequestDTO;
import com.arquiteturaweb.estoque.entities.dto.pedido.PedidoResponseDTO;
import com.arquiteturaweb.estoque.entities.Produto;
import com.arquiteturaweb.estoque.entities.dto.produto.ProdutoResponseDTO;
import com.arquiteturaweb.estoque.repositories.FornecedorRepository;
import com.arquiteturaweb.estoque.repositories.PedidoRepository;
import com.arquiteturaweb.estoque.services.exceptions.DatabaseException;
import com.arquiteturaweb.estoque.services.exceptions.ResourceNotFoundException;

@Service
public class PedidoService {
    
    @Autowired
    private PedidoRepository pedidoRepositorio;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    //MÉTODOS GET

    //FUNÇÃO DE LISTAR TODOS OS PEDIDOS
    public List<PedidoResponseDTO> findAll() {

        List<Pedido> pedidos = pedidoRepositorio.findAll();

        return pedidos.stream().map(p -> PedidoResponseDTO.converterPedido(p)).collect(Collectors.toList());

    }

    //FUNÇÃO DE LISTAR 1 PEDIDO POR ID
    public PedidoResponseDTO findById(Long id) {

        Optional<Pedido> obj = pedidoRepositorio.findById(id);
        return PedidoResponseDTO.converterPedido(obj.orElseThrow(() -> new ResourceNotFoundException(id)));

    }

    // MÉTODO POST
    public PedidoResponseDTO save(PedidoRequestDTO requestObj){
        try{
            Movimentacao movimentacao = movimentacaoRepository.findAllById(requestObj.getMovimentacaoId());
    
            Fornecedor fornecedor = fornecedorRepository.findById(requestObj.getFornecedorIdPedidoRequest()).orElseThrow(() -> new ResourceNotFoundException(requestObj.getFornecedorIdPedidoRequest()));
    
            Pedido pedido = new Pedido(null, requestObj.getValorTotalRequestPedido(), fornecedor, movimentacao);

            Pedido pedidoNovo = pedidoRepositorio.save(pedido);
        }catch (RuntimeException e) {
            e.printStackTrace();
            if(requestObj.getFornecedorIdPedidoRequest() == null){
            throw new ResourceNotFoundException(requestObj.getFornecedorIdPedidoRequest());
            }
            if(requestObj.getMovimentacaoIdPedidoRequest() == null){
            throw new ResourceNotFoundException(requestObj.getMovimentacaoIdPedidoRequest());
            }
        }catch (InterruptedException e) {
            throw new DatabaseException(e.getMessage());
        }
    }


}

