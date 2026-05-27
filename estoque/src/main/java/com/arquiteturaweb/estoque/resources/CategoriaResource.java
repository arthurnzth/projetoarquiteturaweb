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

import com.arquiteturaweb.estoque.entities.dto.categoria.CategoriaRequestDTO;
import com.arquiteturaweb.estoque.entities.dto.categoria.CategoriaResponseDTO;
import com.arquiteturaweb.estoque.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService service;

    // findAll
    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> findAll() {
        List<CategoriaResponseDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    // findById
    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoriaResponseDTO> findById(@PathVariable Long id) {
        CategoriaResponseDTO obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    // insert
    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> insert(@RequestBody CategoriaRequestDTO obj) {
        
        CategoriaResponseDTO responseObj = service.insert(obj);
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
    public ResponseEntity<CategoriaResponseDTO> update(@PathVariable Long id, @RequestBody CategoriaRequestDTO obj) {
        CategoriaResponseDTO reponseObj = service.update(id, obj);
        return ResponseEntity.ok().body(reponseObj);
    }

}
