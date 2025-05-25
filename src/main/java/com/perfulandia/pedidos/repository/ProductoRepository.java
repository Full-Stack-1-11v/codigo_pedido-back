package com.perfulandia.pedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.perfulandia.pedidos.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
