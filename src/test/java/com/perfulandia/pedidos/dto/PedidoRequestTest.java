package com.perfulandia.pedidos.dto;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")

class PedidoRequestTest {

    @Test
    void testConstructorAndGetters() {
        PedidoRequest request = new PedidoRequest(1L, 2L, List.of(101L, 102L));

        assertEquals(1L, request.getUsuarioId());
        assertEquals(2L, request.getSucursalId());
        assertEquals(2, request.getProductos().size());
    }

    @Test
    void testSetters() {
        PedidoRequest request = new PedidoRequest();
        request.setUsuarioId(10L);
        request.setSucursalId(20L);
        request.setProductos(List.of(111L));

        assertEquals(10L, request.getUsuarioId());
        assertEquals(20L, request.getSucursalId());
        assertEquals(1, request.getProductos().size());
        assertEquals(111L, request.getProductos().get(0));
    }

    @Test
    void testEmptyConstructor() {
        PedidoRequest request = new PedidoRequest();
        assertNull(request.getUsuarioId());
        assertNull(request.getSucursalId());
        assertNull(request.getProductos());
    }
}
