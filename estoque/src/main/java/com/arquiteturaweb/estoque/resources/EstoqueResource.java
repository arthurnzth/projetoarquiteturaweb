package com.arquiteturaweb.estoque.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arquiteturaweb.estoque.entities.Estoque;
import com.arquiteturaweb.estoque.services.EstoqueService;

@RestController
@RequestMapping(value = "/estoques")
public class EstoqueResource {

    @Autowired
    private EstoqueService service;

    // findAll
    @GetMapping
    public ResponseEntity<List<Estoque>> findAll() {
        List<Estoque> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    // findById
    @GetMapping(value = "/{id}")
    public ResponseEntity<Estoque> findById(@PathVariable Long id) {
        Estoque obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

}
