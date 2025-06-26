package com.perfulandia.pedidos.dto;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")

class ProductoDTOTest {

    @Test
    void testConstructorConArgumentos() {
        ProductoDTO producto = new ProductoDTO(1L, "Perfume Aqua", 2);
        assertEquals(1L, producto.getId());
        assertEquals("Perfume Aqua", producto.getNombre());
        assertEquals(2, producto.getCantidad());
    }

    @Test
    void testConstructorVacioYSetters() {
        ProductoDTO producto = new ProductoDTO();
        producto.setId(2L);
        producto.setNombre("Fragancia Intensa");
        producto.setCantidad(5);

        assertEquals(2L, producto.getId());
        assertEquals("Fragancia Intensa", producto.getNombre());
        assertEquals(5, producto.getCantidad());
    }
}
