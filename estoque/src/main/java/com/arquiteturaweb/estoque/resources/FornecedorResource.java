package com.arquiteturaweb.estoque.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arquiteturaweb.estoque.entities.Fornecedor;
import com.arquiteturaweb.estoque.entities.Produto;
import com.arquiteturaweb.estoque.repositories.FornecedorRepository;

@RestController
@RequestMapping(value = "/fornecedor")
public class FornecedorResource {
    
    private final FornecedorService service;
    
    public FornecedorResource(FornecedorService service){
        this.service = service;
    }

    @GetMapping
    public List<Fornecedor> listar(){
        return service.listar();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Fornecedor> findById(@PathVariable Long id) {
        Fornecedor obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public Fornecedor salvar(@RequestBody Fornecedor fornecedor) {
        return service.salvar(fornecedor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>atualizar(@PathVariable Long id, @RequestBody Fornecedor fornecedor){
        try {
            Fornecedor atualizado = service.atualizar(id, fornecedor);
            return ResponseEntity.ok(atualizado);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable long id) {
        try {
            service.deletar(id);
            return ResponseEntity.ok("Produto removido com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

}
