package com.perfulandia.pedidos.feign;

import com.perfulandia.pedidos.dto.ProductoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Cliente Feign para el microservicio de productos.
 */
@FeignClient(name = "producto-client", url = "${producto.service.url}")
public interface ProductoClient {

    /**
     * Obtiene un producto por ID.
     */
    @GetMapping("/{id}")
    ProductoDTO obtenerProducto(@PathVariable("id") Long id);
}
