package com.arquiteturaweb.estoque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arquiteturaweb.estoque.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
