package com.perfulandia.pedidos.dto;

import lombok.Data;

/**
 * DTO para representar un usuario en el sistema.
 * Contiene información básica del usuario como ID, nombre y email.
 */
@Data
public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String email;
}