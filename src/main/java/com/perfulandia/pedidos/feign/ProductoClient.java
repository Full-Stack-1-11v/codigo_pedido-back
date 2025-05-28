package com.perfulandia.pedidos.feign;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventario-service", url = "http://localhost:8082")
public interface ProductoClient {
    @GetMapping("/productos/{id}")
    Object getProductoById(@PathVariable("id") Long id);
}

