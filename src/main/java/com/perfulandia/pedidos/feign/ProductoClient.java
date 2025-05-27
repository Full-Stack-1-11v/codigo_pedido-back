package com.perfulandia.pedidos.feign;

import com.perfulandia.pedidos.dto.ProductoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "inventario")
public interface ProductoClient {

    @GetMapping("/productos/{id}")
    ProductoDTO obtenerProducto(@PathVariable("id") Long id);

    @GetMapping("/productos")
    List<ProductoDTO> listarProductos();
}
