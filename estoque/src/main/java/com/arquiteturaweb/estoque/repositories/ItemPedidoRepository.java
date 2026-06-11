package com.arquiteturaweb.estoque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arquiteturaweb.estoque.entities.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long>{

}
