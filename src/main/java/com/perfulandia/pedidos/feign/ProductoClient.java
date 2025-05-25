package com.perfulandia.pedidos.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.perfulandia.pedidos.model.Producto;

@FeignClient(name = "producto-service")
public interface ProductoClient {
    @GetMapping("/productos/{id}")
    Producto obtenerProducto(@PathVariable Long id);
}