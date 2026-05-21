package com.arquiteturaweb.estoque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arquiteturaweb.estoque.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
