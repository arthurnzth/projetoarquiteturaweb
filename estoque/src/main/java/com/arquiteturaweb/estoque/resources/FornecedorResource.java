package com.arquiteturaweb.estoque.resources;

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

import com.arquiteturaweb.estoque.entities.dto.fornecedor.FornecedorRequestDTO;
import com.arquiteturaweb.estoque.entities.dto.fornecedor.FornecedorResponseDTO;
import com.arquiteturaweb.estoque.services.FornecedorService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/fornecedor")
public class FornecedorResource {
    
    @Autowired
    private FornecedorService service;

    @GetMapping
    public ResponseEntity<List<FornecedorResponseDTO>> findAll(){
        List<FornecedorResponseDTO> fornecedorLista = service.findAll();
        return ResponseEntity.ok().body(fornecedorLista);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<FornecedorResponseDTO> findById(@PathVariable Long id) {
        FornecedorResponseDTO obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<FornecedorResponseDTO> salvar(@RequestBody FornecedorRequestDTO requestFornecedor) {
        
        FornecedorResponseDTO responseObj = service.save(requestFornecedor);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(responseObj.getId()).toUri();
        return ResponseEntity.created(uri).body(responseObj);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<FornecedorResponseDTO> atualizar(@PathVariable Long id, @RequestBody FornecedorRequestDTO requestFornecedor){
        
            FornecedorResponseDTO atualizado = service.update(id, requestFornecedor);
            return ResponseEntity.ok(atualizado);
        
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable long id) {
        try {
            service.delete(id);
            return ResponseEntity.ok("Fornecedor removido com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

}
