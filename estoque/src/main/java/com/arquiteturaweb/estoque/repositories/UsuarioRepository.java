package com.arquiteturaweb.estoque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.arquiteturaweb.estoque.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}