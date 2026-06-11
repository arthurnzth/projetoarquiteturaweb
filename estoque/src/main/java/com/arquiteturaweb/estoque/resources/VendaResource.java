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

import com.arquiteturaweb.estoque.entities.dto.venda.VendaRequestDTO;
import com.arquiteturaweb.estoque.entities.dto.venda.VendaResponseDTO;
import com.arquiteturaweb.estoque.services.VendaService;

@RestController
@RequestMapping(value = "/vendas")
public class VendaResource {

    @Autowired
    private VendaService service;

    // findALl
    @GetMapping
    public ResponseEntity<List<VendaResponseDTO>> findAll() {
        List<VendaResponseDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    // findById
    @GetMapping(value = "/{id}")
    public ResponseEntity<VendaResponseDTO> findById(@PathVariable Long id) {
        VendaResponseDTO reponseObj = service.findById(id);
        return ResponseEntity.ok().body(reponseObj);
    }

    // insert
    @PostMapping
    public ResponseEntity<VendaResponseDTO> insert(@RequestBody VendaRequestDTO obj) {

        VendaResponseDTO responseObj = service.insert(obj);
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
    public ResponseEntity<VendaResponseDTO> update(@PathVariable Long id, @RequestBody VendaRequestDTO obj) {

        VendaResponseDTO responseObj = service.update(id, obj);
        return ResponseEntity.ok().body(responseObj);

    }

}
