package com.perfulandia.pedidos.service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perfulandia.pedidos.dto.UsuarioDTO;
import com.perfulandia.pedidos.mapper.UsuarioMapper;
import com.perfulandia.pedidos.model.Rol;
import com.perfulandia.pedidos.model.Usuario;
import com.perfulandia.pedidos.repository.RolRepository;
import com.perfulandia.pedidos.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    public UsuarioDTO crearUsuario(UsuarioDTO dto) {
        Set<Rol> roles = dto.getIdsRoles().stream()
                .map(id -> rolRepository.findById(id).orElseThrow(() -> new RuntimeException("Rol no encontrado: " + id)))
                .collect(Collectors.toSet());

        Usuario usuario = UsuarioMapper.toEntity(dto, roles);
        Usuario guardado = usuarioRepository.save(usuario);
        return UsuarioMapper.toDTO(guardado);
    }

    public Optional<UsuarioDTO> obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(UsuarioMapper::toDTO);
    }

    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
