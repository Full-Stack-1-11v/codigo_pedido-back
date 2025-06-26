package com.perfulandia.pedidos.dto;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")

class PedidoResponseTest {

    @Test
    void testConstructorConArgumentos() {
        PedidoResponse response = new PedidoResponse("Pedido creado exitosamente", true);
        assertEquals("Pedido creado exitosamente", response.getMensaje());
        assertTrue(response.isExitoso());
    }

    @Test
    void testConstructorVacioYSetters() {
        PedidoResponse response = new PedidoResponse();
        response.setMensaje("Error en el pedido");
        response.setExitoso(false);

        assertEquals("Error en el pedido", response.getMensaje());
        assertFalse(response.isExitoso());
    }
}
