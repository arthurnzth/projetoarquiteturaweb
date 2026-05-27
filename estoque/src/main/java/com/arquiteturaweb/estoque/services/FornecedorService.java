package com.arquiteturaweb.estoque.services;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.arquiteturaweb.estoque.entities.Fornecedor;
import com.arquiteturaweb.estoque.entities.dto.fornecedor.FornecedorRequestDTO;
import com.arquiteturaweb.estoque.entities.dto.fornecedor.FornecedorResponseDTO;
import com.arquiteturaweb.estoque.repositories.FornecedorRepository;
import com.arquiteturaweb.estoque.services.exceptions.DatabaseException;
import com.arquiteturaweb.estoque.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepositorio;

    public FornecedorService(FornecedorRepository repository) {
        this.fornecedorRepositorio = repository;
    }

    // FUNCTIONS GET
    public List<FornecedorResponseDTO> findAll() {

        List<Fornecedor> fornecedores = fornecedorRepositorio.findAll();

        return fornecedores.stream().map(f -> FornecedorResponseDTO.converterFornecedor(f))
                .collect(Collectors.toList());
    }

    public FornecedorResponseDTO findById(Long id) {
        Optional<Fornecedor> objeto = fornecedorRepositorio.findById(id);
        return FornecedorResponseDTO.converterFornecedor(objeto.orElseThrow(() -> new ResourceNotFoundException(id)));
    }

    // FUNCTIONS POST
    public FornecedorResponseDTO save(FornecedorRequestDTO requestObj) {
        
        try{
            Fornecedor fornecedor = new Fornecedor(null, requestObj.getNome(), requestObj.getEndereco(), requestObj.getTelefone());

            fornecedorRepositorio.save(fornecedor);

            FornecedorResponseDTO responseObj = FornecedorResponseDTO.converterFornecedor(fornecedor);
            
            return responseObj;
        }catch(DataIntegrityViolationException e) {
            throw new RuntimeException("Dados inválidos para salvar fornecedor");
        }
    }



    // FUNCTIONS PUT
    public FornecedorResponseDTO update(Long id, FornecedorRequestDTO requestData){
        try{
            Fornecedor fornecedor = fornecedorRepositorio.getReferenceById(id);
            updateData(fornecedor, requestData);
            return FornecedorResponseDTO.converterFornecedor(fornecedorRepositorio.save(fornecedor));
        }catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Fornecedor entity, FornecedorRequestDTO obj){
        try {
            entity.setNomeFornecedor(obj.getNome());
            entity.setEnderecoFornecedor(obj.getEndereco());
            entity.setTelefoneFornecedor(obj.getTelefone());
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Dados inválidos para salvar fornecedor");
        }
    }

    // FUNCTIONS DELETE
    public void delete(Long id) {
        try {
            fornecedorRepositorio.deleteById(id);
        
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);

        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());

        }
    }

}
