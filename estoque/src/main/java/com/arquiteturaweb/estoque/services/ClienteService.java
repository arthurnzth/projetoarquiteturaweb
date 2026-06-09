package com.arquiteturaweb.estoque.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.arquiteturaweb.estoque.entities.Cliente;
import com.arquiteturaweb.estoque.entities.dto.cliente.ClienteRequestDTO;
import com.arquiteturaweb.estoque.entities.dto.cliente.ClienteResponseDTO;
import com.arquiteturaweb.estoque.repositories.ClienteRepository;
import com.arquiteturaweb.estoque.services.exceptions.DatabaseException;
import com.arquiteturaweb.estoque.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteResponseDTO> findAll(){
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream().map(c -> ClienteResponseDTO.converterCliente(c)).collect(Collectors.toList());
    }

    public ClienteResponseDTO findById(Long id) {
        Optional<Cliente> obj = clienteRepository.findById(id);
        return ClienteResponseDTO.converterCliente(obj.orElseThrow(() -> new ResourceNotFoundException(id)));
    }

    public ClienteResponseDTO insert(ClienteRequestDTO obj) {
        Cliente cliente = new Cliente(null, obj.getNome(), obj.getCnpj(), obj.getContato());
        return ClienteResponseDTO.converterCliente(clienteRepository.save(cliente));
    }

    public void delete(Long id){
        try{
            clienteRepository.deleteById(id);

        }catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);

        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());

        }
    }

    public ClienteResponseDTO update(Long id, ClienteRequestDTO obj){
        try {
            Cliente entity = clienteRepository.getReferenceById(id);
            updateData(entity, obj);
            return ClienteResponseDTO.converterCliente(clienteRepository.save(entity));

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);

        }
    }

    private void updateData(Cliente entity, ClienteRequestDTO obj) {
        entity.setNome(obj.getNome());
        entity.setCnpj(obj.getCnpj());
        entity.setContato(obj.getContato());
    }
}
