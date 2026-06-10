package com.arquiteturaweb.estoque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.arquiteturaweb.estoque.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{

}