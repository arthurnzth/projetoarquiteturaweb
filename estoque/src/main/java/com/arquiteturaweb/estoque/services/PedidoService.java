package com.arquiteturaweb.estoque.services;

import java.util.stream.Collectors;
import java.util.Optional;
import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.arquiteturaweb.estoque.entities.Fornecedor;
import com.arquiteturaweb.estoque.entities.ItemPedido;
import com.arquiteturaweb.estoque.entities.Movimentacao;
import com.arquiteturaweb.estoque.entities.Pedido;
import com.arquiteturaweb.estoque.entities.Usuario;
import com.arquiteturaweb.estoque.entities.dto.itemPedido.ItemPedidoRequestDTO;
import com.arquiteturaweb.estoque.entities.dto.pedido.PedidoRequestDTO;
import com.arquiteturaweb.estoque.entities.dto.pedido.PedidoResponseDTO;
import com.arquiteturaweb.estoque.repositories.FornecedorRepository;
import com.arquiteturaweb.estoque.repositories.ItemPedidoRepository;
import com.arquiteturaweb.estoque.repositories.MovimentacaoRepository;
import com.arquiteturaweb.estoque.repositories.PedidoRepository;
import com.arquiteturaweb.estoque.repositories.ProdutoRepository;
import com.arquiteturaweb.estoque.repositories.UsuarioRepository;
import com.arquiteturaweb.estoque.services.exceptions.DatabaseException;
import com.arquiteturaweb.estoque.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PedidoService {
    
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    //MÉTODOS GET

    //FUNÇÃO DE LISTAR TODOS OS PEDIDOS
    public List<PedidoResponseDTO> findAll() {

        List<Pedido> pedidos = pedidoRepository.findAll();

        return pedidos.stream().map(p -> PedidoResponseDTO.converterPedido(p)).collect(Collectors.toList());

    }

    //FUNÇÃO DE LISTAR 1 PEDIDO POR ID
    public PedidoResponseDTO findById(Long id) {

        Optional<Pedido> obj = pedidoRepository.findById(id);
        return PedidoResponseDTO.converterPedido(obj.orElseThrow(() -> new ResourceNotFoundException(id)));

    }

    // MÉTODO POST
    public PedidoResponseDTO insert(PedidoRequestDTO requestObj) {

        try {
            Fornecedor fornecedor = fornecedorRepository.getReferenceById(requestObj.getFornecedorId());

            Instant data = Instant.parse(requestObj.getData());

            Usuario responsavel = usuarioRepository.getReferenceById(requestObj.getResponsavelId());

            Pedido pedido = new Pedido(null, fornecedor, data, null, responsavel);

            for (ItemPedidoRequestDTO i : requestObj.getItens()) {
                ItemPedido item = new ItemPedido(pedido, produtoRepository.getReferenceById(i.getProdutoId()), i.getQuantidade(), produtoRepository.getReferenceById(i.getProdutoId()).getPreco());
                itemPedidoRepository.save(item);
                pedido.getItens().add(item);
            }

            Pedido pedidoSalvo = pedidoRepository.save(pedido);

            Movimentacao movimentacao = new Movimentacao(null, data, pedidoSalvo, responsavel, requestObj.getObservacao());
            movimentacaoRepository.save(movimentacao);

            PedidoResponseDTO responseObj = PedidoResponseDTO.converterPedido(pedidoSalvo);
            return responseObj;

        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new ResourceNotFoundException(requestObj.getFornecedorId());
        }

    }

    //MÉTODO PUT
    public PedidoResponseDTO update(Long id, PedidoRequestDTO obj){
        try {
            Pedido entity = pedidoRepository.getReferenceById(id);
            updateData(entity, obj);
            return PedidoResponseDTO.converterPedido(pedidoRepository.save(entity));
        
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Pedido entity, PedidoRequestDTO obj) {

        entity.setFornecedorPedido(fornecedorRepository.getReferenceById(obj.getFornecedorId()));
        entity.setDataPedido(Instant.parse(obj.getData()));
        entity.setResponsavel(usuarioRepository.getReferenceById(obj.getResponsavelId()));

    }

    //MÉTODO DELETE
    public void delete(Long id) {

        try {
            pedidoRepository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);

        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());

        }
    }

}

