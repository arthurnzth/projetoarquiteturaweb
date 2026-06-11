package com.arquiteturaweb.estoque.resources;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arquiteturaweb.estoque.entities.dto.movimentacao.MovimentacaoRequestDTO;
import com.arquiteturaweb.estoque.entities.dto.movimentacao.MovimentacaoResponseDTO;
import com.arquiteturaweb.estoque.services.MovimentacaoService;

@RestController
@RequestMapping(value = "/movimentacoes")
public class MovimentacaoResource {

    private MovimentacaoService service;

    @GetMapping
    public ResponseEntity<List<MovimentacaoResponseDTO>> findAll() {
        List<MovimentacaoResponseDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MovimentacaoResponseDTO> findById(@PathVariable Long id) {
        MovimentacaoResponseDTO responseObj = service.findById(id);
        return ResponseEntity.ok().body(responseObj);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<MovimentacaoResponseDTO> update(@PathVariable Long id, @RequestBody MovimentacaoRequestDTO obj) {
        MovimentacaoResponseDTO responseObj = service.update(id, obj);
        return ResponseEntity.ok().body(responseObj);
    }

}
