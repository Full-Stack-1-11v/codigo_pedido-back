package com.perfulandia.pedidos.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Cliente Feign para interactuar con el microservicio de usuarios.
 * Permite validar si un usuario existe y está activo.
 */
@FeignClient(
    name = "usuario-client",
    url = "http://localhost:8080/api/usuarios"
)
public interface UsuarioClient {
    /**
     * Valida si un usuario existe y está activo.
     *
     * @param usuarioId ID del usuario a validar.
     * @return true si el usuario es válido, false en caso contrario.
     */
    @GetMapping("/{id}/validar")
    boolean validarUsuario(@PathVariable("id") Long usuarioId);
}
