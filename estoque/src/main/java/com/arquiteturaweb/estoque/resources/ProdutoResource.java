package com.arquiteturaweb.estoque.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.arquiteturaweb.estoque.entities.dto.produto.ProdutoRequestDTO;
import com.arquiteturaweb.estoque.entities.dto.produto.ProdutoResponseDTO;
import com.arquiteturaweb.estoque.services.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService service;

    // findAll
    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> findAll() {
        List<ProdutoResponseDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    // findById
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProdutoResponseDTO> findById(@PathVariable Long id) {
        ProdutoResponseDTO responseObj = service.findById(id);
        return ResponseEntity.ok().body(responseObj);
    }

    // insert
    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> insert(@RequestBody ProdutoRequestDTO obj) {

        ProdutoResponseDTO responseObj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(responseObj.getId()).toUri();
        return ResponseEntity.created(uri).body(responseObj);

    }

    // delete
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        
        service.delete(id);
        return ResponseEntity.noContent().build();

    }

    // update
    @PutMapping(value = "/{id}")
    public ResponseEntity<ProdutoResponseDTO> update(@PathVariable Long id, @RequestBody ProdutoRequestDTO obj) {

        ProdutoResponseDTO responseObj = service.update(id, obj);
        return ResponseEntity.ok().body(responseObj);

    }

}
