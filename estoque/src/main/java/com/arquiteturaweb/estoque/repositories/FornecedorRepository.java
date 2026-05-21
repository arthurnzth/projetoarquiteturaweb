package com.arquiteturaweb.estoque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.arquiteturaweb.estoque.entities.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {

}