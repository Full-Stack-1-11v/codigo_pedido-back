package com.perfulandia.pedidos.dto;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")

class UsuarioDTOTest {

    @Test
    void testConstructorConArgumentos() {
        UsuarioDTO usuario = new UsuarioDTO(1L, "Jorge", "jorge@perfulandia.cl");

        assertEquals(1L, usuario.getId());
        assertEquals("Jorge", usuario.getNombre());
        assertEquals("jorge@perfulandia.cl", usuario.getEmail());
    }

    @Test
    void testConstructorVacioYSetters() {
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setId(2L);
        usuario.setNombre("Paloma");
        usuario.setEmail("paloma@perfume.cl");

        assertEquals(2L, usuario.getId());
        assertEquals("Paloma", usuario.getNombre());
        assertEquals("paloma@perfume.cl", usuario.getEmail());
    }
}
