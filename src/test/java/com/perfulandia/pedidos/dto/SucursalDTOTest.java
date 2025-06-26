package com.perfulandia.pedidos.dto;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")

class SucursalDTOTest {

    @Test
    void testConstructorConArgumentos() {
        SucursalDTO sucursal = new SucursalDTO(1L, "Sucursal Central", "Av. Principal 123");

        assertEquals(1L, sucursal.getId());
        assertEquals("Sucursal Central", sucursal.getNombre());
        assertEquals("Av. Principal 123", sucursal.getDireccion());
    }

    @Test
    void testConstructorVacioYSetters() {
        SucursalDTO sucursal = new SucursalDTO();
        sucursal.setId(2L);
        sucursal.setNombre("Sucursal Norte");
        sucursal.setDireccion("Calle 9 #555");

        assertEquals(2L, sucursal.getId());
        assertEquals("Sucursal Norte", sucursal.getNombre());
        assertEquals("Calle 9 #555", sucursal.getDireccion());
    }
}
