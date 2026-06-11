package com.arquiteturaweb.estoque.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.arquiteturaweb.estoque.entities.Cliente;
import com.arquiteturaweb.estoque.entities.ItemVenda;
import com.arquiteturaweb.estoque.entities.Usuario;
import com.arquiteturaweb.estoque.entities.Venda;
import com.arquiteturaweb.estoque.entities.dto.itemVenda.ItemVendaRequestDTO;
import com.arquiteturaweb.estoque.entities.dto.venda.VendaRequestDTO;
import com.arquiteturaweb.estoque.entities.dto.venda.VendaResponseDTO;
import com.arquiteturaweb.estoque.repositories.ClienteRepository;
import com.arquiteturaweb.estoque.repositories.ItemVendaRepository;
import com.arquiteturaweb.estoque.repositories.ProdutoRepository;
import com.arquiteturaweb.estoque.repositories.UsuarioRepository;
import com.arquiteturaweb.estoque.repositories.VendaRepository;
import com.arquiteturaweb.estoque.services.exceptions.DatabaseException;
import com.arquiteturaweb.estoque.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class VendaService {
    
    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItemVendaRepository itemVendaRepository;

    public List<VendaResponseDTO> findAll() {

        List<Venda> vendas = vendaRepository.findAll();
        return vendas.stream().map(v -> VendaResponseDTO.converterVenda(v)).collect(Collectors.toList());

    }

    public VendaResponseDTO findById(Long id) {

        Optional<Venda> obj = vendaRepository.findById(id);
        return VendaResponseDTO.converterVenda(obj.orElseThrow(() -> new ResourceNotFoundException(id)));

    }

    public VendaResponseDTO insert(VendaRequestDTO requestObj) {
        
        try {
            Cliente cliente = clienteRepository.getReferenceById(requestObj.getClienteId());

            Instant data = Instant.parse(requestObj.getData());

            Usuario responsavel = usuarioRepository.getReferenceById(requestObj.getResponsavelId());

            Venda venda = new Venda(null, cliente, data, null, responsavel);

            for (ItemVendaRequestDTO i : requestObj.getItens()) {
                ItemVenda item = new ItemVenda(venda, produtoRepository.getReferenceById(i.getProdutoId()), i.getQuantidade(), produtoRepository.getReferenceById(i.getProdutoId()).getPreco());
                itemVendaRepository.save(item);
                venda.getItens().add(item);
            }

            Venda vendaSalva = vendaRepository.save(venda);

            // TODO: event: criação de Movimentacao

            VendaResponseDTO responseObj = VendaResponseDTO.converterVenda(vendaSalva);
            return responseObj;

        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new ResourceNotFoundException(requestObj.getClienteId());
            
        }

    }

    public void delete(Long id) {

        try {
            vendaRepository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);

        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());

        }

    }

    public VendaResponseDTO update(Long id, VendaRequestDTO obj) {

        try {
            Venda entity = vendaRepository.getReferenceById(id);
            updateData(entity, obj);
            return VendaResponseDTO.converterVenda(vendaRepository.save(entity));

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);

        }

    }

    private void updateData(Venda entity, VendaRequestDTO obj) {

        entity.setClienteVenda(clienteRepository.getReferenceById(obj.getClienteId()));
        entity.setDataVenda(Instant.parse(obj.getData()));
        entity.setResponsavelVenda(usuarioRepository.getReferenceById(obj.getResponsavelId()));

    }

}
