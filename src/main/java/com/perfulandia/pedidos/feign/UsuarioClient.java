package com.perfulandia.pedidos.feign;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "usuario-service", url = "http://localhost:8081")
public interface UsuarioClient {
    @GetMapping("/usuarios/{id}")
    Object getUsuarioById(@PathVariable("id") Long id);
}
