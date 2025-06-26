package com.perfulandia.pedidos.dto;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")

class AlertaInventarioTest {

    @Test
    void testConstructorAndGetters() {
        LocalDateTime now = LocalDateTime.now();
        AlertaInventario alerta = new AlertaInventario(1L, 10L, 5L, 8, "Stock bajo", now);

        assertEquals(1L, alerta.getId());
        assertEquals(10L, alerta.getProductoId());
        assertEquals(5L, alerta.getSucursalId());
        assertEquals(8, alerta.getStockActual());
        assertEquals("Stock bajo", alerta.getMensaje());
        assertEquals(now, alerta.getFechaHora());
    }

    @Test
    void testSetters() {
        AlertaInventario alerta = new AlertaInventario();
        LocalDateTime fecha = LocalDateTime.now();

        alerta.setId(2L);
        alerta.setProductoId(20L);
        alerta.setSucursalId(3L);
        alerta.setStockActual(2);
        alerta.setMensaje("Sin stock");
        alerta.setFechaHora(fecha);

        assertEquals(2L, alerta.getId());
        assertEquals(20L, alerta.getProductoId());
        assertEquals(3L, alerta.getSucursalId());
        assertEquals(2, alerta.getStockActual());
        assertEquals("Sin stock", alerta.getMensaje());
        assertEquals(fecha, alerta.getFechaHora());
    }
}
