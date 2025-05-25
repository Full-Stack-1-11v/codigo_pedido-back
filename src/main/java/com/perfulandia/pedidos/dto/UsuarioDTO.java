package com.perfulandia.pedidos.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String rut;
    private String correo;
    private String direccion;
    private String password;
    private boolean activo;
    private Set<Long> idsRoles; // solo pasamos los IDs de los roles
    private String rawPassword;
}