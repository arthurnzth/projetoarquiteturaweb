package com.arquiteturaweb.estoque.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.arquiteturaweb.estoque.entities.Categoria;
import com.arquiteturaweb.estoque.entities.dto.categoria.CategoriaRequestDTO;
import com.arquiteturaweb.estoque.entities.dto.categoria.CategoriaResponseDTO;
import com.arquiteturaweb.estoque.repositories.CategoriaRepository;
import com.arquiteturaweb.estoque.services.exceptions.DatabaseException;
import com.arquiteturaweb.estoque.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<CategoriaResponseDTO> findAll() {

        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias.stream().map(c -> CategoriaResponseDTO.converterCategoria(c)).collect(Collectors.toList());

    }

    public CategoriaResponseDTO findById(Long id) {

        Optional<Categoria> obj = categoriaRepository.findById(id);
        return CategoriaResponseDTO.converterCategoria(obj.orElseThrow(() -> new ResourceNotFoundException(id)));

    }

    public CategoriaResponseDTO insert(CategoriaRequestDTO obj) {

        Categoria categoria = new Categoria(null, obj.getNome());
        return CategoriaResponseDTO.converterCategoria(categoriaRepository.save(categoria));

    }

    public void delete(Long id) {

        try {
            categoriaRepository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);

        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());

        }

    }

    public CategoriaResponseDTO update(Long id, CategoriaRequestDTO obj) {

        try {
            Categoria entity = categoriaRepository.getReferenceById(id);
            updateData(entity, obj);
            return CategoriaResponseDTO.converterCategoria(categoriaRepository.save(entity));

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);

        }

    }

    private void updateData(Categoria entity, CategoriaRequestDTO obj) {
        entity.setNome(obj.getNome());
    }

}
