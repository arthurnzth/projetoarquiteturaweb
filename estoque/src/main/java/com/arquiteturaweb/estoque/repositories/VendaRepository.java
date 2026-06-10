package com.arquiteturaweb.estoque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.arquiteturaweb.estoque.entities.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long>{
    
}
