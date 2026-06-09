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

import com.arquiteturaweb.estoque.entities.dto.pedido.PedidoRequestDTO;
import com.arquiteturaweb.estoque.entities.dto.pedido.PedidoResponseDTO;
import com.arquiteturaweb.estoque.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {
    
    @Autowired
    private  PedidoService service;

    //MÉTODO GET

    // findAll
    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> findAll() {
        List<PedidoResponseDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    // findById
    @GetMapping(value = "/{id}")
    public ResponseEntity<PedidoResponseDTO> findById(@PathVariable Long id) {
        PedidoResponseDTO responseObj = service.findById(id);
        return ResponseEntity.ok().body(responseObj);
    }

    //MÉTODO POST

    // Save
    //@PostMapping
    //public ResponseEntity<PedidoResponseDTO> save(@RequestBody PedidoRequestDTO obj) {
        //PedidoResponseDTO responseObj = service.save(obj);
        //URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(responseObj.getIdPedidoResponse()).toUri();
        //return ResponseEntity.created(uri).body(responseObj);
    //}


    //MÉTODO PUT
    // update
    @PutMapping(value = "/{id}")
    public ResponseEntity<PedidoResponseDTO> update(@PathVariable Long id, @RequestBody PedidoRequestDTO obj) {
        PedidoResponseDTO responseObj = service.update(id, obj);
        return ResponseEntity.ok().body(responseObj);
    }

    //MÉTODO DELETE
     // delete
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
