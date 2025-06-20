package com.perfulandia.pedidos.feign;

import com.perfulandia.pedidos.dto.ProductoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Cliente Feign para interactuar con el microservicio de productos.
 * Permite obtener información de un producto por su ID.
 */
@FeignClient(name = "producto-client", url = "http://localhost:8084/api/productos")
public interface ProductoClient {
    /**
     * Obtiene un producto por su ID.
     *
     * @param id ID del producto a obtener.
     * @return ProductoDTO con la información del producto.
     */
    @GetMapping("/{id}")
    ProductoDTO obtenerProducto(@PathVariable Long id);
}
