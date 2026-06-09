package com.arquiteturaweb.estoque.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.arquiteturaweb.estoque.entities.Usuario;
import com.arquiteturaweb.estoque.entities.dto.usuario.UsuarioRequestDTO;
import com.arquiteturaweb.estoque.entities.dto.usuario.UsuarioResponseDTO;
import com.arquiteturaweb.estoque.repositories.UsuarioRepository;
import com.arquiteturaweb.estoque.services.exceptions.DatabaseException;
import com.arquiteturaweb.estoque.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioResponseDTO> findAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(u -> UsuarioResponseDTO.converterUsuario(u)).collect(Collectors.toList());
    }

    public UsuarioResponseDTO findById(Long id) {
        Optional<Usuario> obj = usuarioRepository.findById(id);
        return UsuarioResponseDTO.converterUsuario(obj.orElseThrow(() -> new ResourceNotFoundException(id)));
    }

    public UsuarioResponseDTO insert(UsuarioRequestDTO obj) {
        boolean ativo = obj.getAtivo() == 1 ? true : false;
        Usuario usuario = new Usuario(null, obj.getNome(), obj.getEmail(), obj.getCargo(), ativo);
        return UsuarioResponseDTO.converterUsuario(usuarioRepository.save(usuario));
    }

    public void delete(Long id){
        try{
            usuarioRepository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);

        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());

        }
    }

    public UsuarioResponseDTO update(Long id, UsuarioRequestDTO obj){
        try {
            Usuario entity = usuarioRepository.getReferenceById(id);
            updateData(entity, obj);
            return UsuarioResponseDTO.converterUsuario(usuarioRepository.save(entity));

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);

        }
    }

    private void updateData(Usuario entity, UsuarioRequestDTO obj) {
        entity.setNome(obj.getNome());
        entity.setEmail(obj.getEmail());
        entity.setCargo(obj.getCargo());
        entity.setAtivo(obj.getAtivo() == 1 ? true : false);
    }
}
