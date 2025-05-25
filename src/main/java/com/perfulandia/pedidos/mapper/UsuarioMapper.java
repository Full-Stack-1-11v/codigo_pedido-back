package com.perfulandia.pedidos.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import com.perfulandia.pedidos.dto.UsuarioDTO;
import com.perfulandia.pedidos.model.Rol;
import com.perfulandia.pedidos.model.Usuario;

public class UsuarioMapper {

    public static UsuarioDTO toDTO(Usuario usuario) {
        return UsuarioDTO.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .rut(usuario.getRut())
                .correo(usuario.getCorreo())
                .direccion(usuario.getDireccion())
                .password(usuario.getPassword())
                .activo(usuario.isActivo())
                .idsRoles(usuario.getRoles().stream().map(Rol::getId).collect(Collectors.toSet()))
                .build();
    }

    public static Usuario toEntity(UsuarioDTO dto, Set<Rol> roles) {
        return Usuario.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .apellido(dto.getApellido())
                .rut(dto.getRut())
                .correo(dto.getCorreo())
                .direccion(dto.getDireccion())
                .password(dto.getPassword())
                .activo(dto.isActivo())
                .roles(roles)
                .build();
    }
}
