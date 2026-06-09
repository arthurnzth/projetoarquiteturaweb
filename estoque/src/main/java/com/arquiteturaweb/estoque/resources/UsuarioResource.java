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

import com.arquiteturaweb.estoque.entities.dto.usuario.UsuarioRequestDTO;
import com.arquiteturaweb.estoque.entities.dto.usuario.UsuarioResponseDTO;
import com.arquiteturaweb.estoque.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {
    
    @Autowired
    private UsuarioService service;

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> findAll(){
        List<UsuarioResponseDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UsuarioResponseDTO> findById(@PathVariable Long id){
        UsuarioResponseDTO responseObj = service.findById(id);
        return ResponseEntity.ok().body(responseObj);
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> insert(@RequestBody UsuarioRequestDTO obj){
        UsuarioResponseDTO reponseObj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(reponseObj.getId()).toUri();
        return ResponseEntity.created(uri).body(reponseObj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UsuarioResponseDTO> update(@PathVariable Long id, @RequestBody UsuarioRequestDTO obj) {
        UsuarioResponseDTO reponseObj = service.update(id, obj);
        return ResponseEntity.ok().body(reponseObj);
    }

}
