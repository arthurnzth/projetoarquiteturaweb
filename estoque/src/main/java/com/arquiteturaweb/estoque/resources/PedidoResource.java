package com.arquiteturaweb.estoque.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
