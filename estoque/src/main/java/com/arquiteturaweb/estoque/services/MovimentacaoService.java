package com.arquiteturaweb.estoque.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arquiteturaweb.estoque.entities.Movimentacao;
import com.arquiteturaweb.estoque.entities.dto.movimentacao.MovimentacaoRequestDTO;
import com.arquiteturaweb.estoque.entities.dto.movimentacao.MovimentacaoResponseDTO;
import com.arquiteturaweb.estoque.repositories.MovimentacaoRepository;
import com.arquiteturaweb.estoque.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MovimentacaoService {

    @Autowired
    MovimentacaoRepository movimentacaoRepository;

    public List<MovimentacaoResponseDTO> findAll() {
        List<Movimentacao> movimentacoes = movimentacaoRepository.findAll();
        return movimentacoes.stream().map(m -> MovimentacaoResponseDTO.converterMovimentacao(m)).collect(Collectors.toList());
    }

    public MovimentacaoResponseDTO findById(Long id) {
        Optional<Movimentacao> obj = movimentacaoRepository.findById(id);
        return MovimentacaoResponseDTO.converterMovimentacao(obj.orElseThrow(() -> new ResourceNotFoundException(id)));
    }

    public MovimentacaoResponseDTO update(Long id, MovimentacaoRequestDTO obj) {
        try {
            Movimentacao entity = movimentacaoRepository.getReferenceById(id);
            updateData(entity, obj);
            return MovimentacaoResponseDTO.converterMovimentacao(movimentacaoRepository.save(entity));

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);

        }
    }

    private void updateData(Movimentacao entity, MovimentacaoRequestDTO obj) {
        entity.setObservacao(obj.getObservacao());;
    }

}
