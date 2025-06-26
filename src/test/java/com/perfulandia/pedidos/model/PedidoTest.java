package com.perfulandia.pedidos.model;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")

class PedidoTest {

    @Test
    void testGettersAndSetters() {
        Pedido pedido = new Pedido();

        pedido.setId(10L);
        pedido.setUsuarioId(1L);
        pedido.setSucursalId(2L);
        pedido.setProductos(List.of(100L, 200L));
        pedido.setEstado("CONFIRMADO");

        assertEquals(10L, pedido.getId());
        assertEquals(1L, pedido.getUsuarioId());
        assertEquals(2L, pedido.getSucursalId());
        assertEquals(List.of(100L, 200L), pedido.getProductos());
        assertEquals("CONFIRMADO", pedido.getEstado());
    }
}
