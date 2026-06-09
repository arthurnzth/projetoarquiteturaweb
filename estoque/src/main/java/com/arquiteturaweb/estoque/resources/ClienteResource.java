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

import com.arquiteturaweb.estoque.entities.dto.cliente.ClienteRequestDTO;
import com.arquiteturaweb.estoque.entities.dto.cliente.ClienteResponseDTO;
import com.arquiteturaweb.estoque.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
    
    @Autowired
    private ClienteService service;

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> findAll(){
        List<ClienteResponseDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteResponseDTO> findById(@PathVariable Long id){
        ClienteResponseDTO reponseObj = service.findById(id);
        return ResponseEntity.ok().body(reponseObj);
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> insert(@RequestBody ClienteRequestDTO obj){
        ClienteResponseDTO reponseObj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(reponseObj.getId()).toUri();
        return ResponseEntity.created(uri).body(reponseObj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClienteResponseDTO> update(@PathVariable Long id, @RequestBody ClienteRequestDTO obj) {
        ClienteResponseDTO reponseObj = service.update(id, obj);
        return ResponseEntity.ok().body(reponseObj);
    }

}
