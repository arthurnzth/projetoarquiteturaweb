package com.arquiteturaweb.estoque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arquiteturaweb.estoque.entities.Estoque;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

}
