package com.perfulandia.pedidos.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Cliente Feign para el microservicio de usuarios.
 */
@FeignClient(name = "usuario-client", url = "${usuario.service.url}")
public interface UsuarioClient {

    /**
     * Valida si un usuario existe y está activo.
     */
    @GetMapping("/{id}/validar")
    boolean validarUsuario(@PathVariable("id") Long usuarioId);
}
