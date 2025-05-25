package com.perfulandia.pedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.perfulandia.pedidos.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}