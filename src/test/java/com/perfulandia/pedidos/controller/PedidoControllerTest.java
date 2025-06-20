package com.perfulandia.pedidos.controller;

import com.perfulandia.pedidos.dto.PedidoRequest;
import com.perfulandia.pedidos.model.Pedido;
import com.perfulandia.pedidos.service.PedidoService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List; // ✅ Import correcto

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Pruebas unitarias para el controlador de pedidos.
 * Verifica la creación de pedidos, cambio de estado y listado de pedidos.
 */
@WebMvcTest(PedidoController.class)
class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PedidoService pedidoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCrearPedido() throws Exception {
        PedidoRequest request = new PedidoRequest();
        request.setUsuarioId(1L);
        request.setSucursalId(2L);
        request.setProductos(List.of(1L, 2L));

        Pedido pedido = new Pedido();
        pedido.setUsuarioId(1L);
        pedido.setSucursalId(2L);
        pedido.setProductos(List.of(1L, 2L));
        pedido.setEstado("CONFIRMADO");

        Mockito.when(pedidoService.crearPedido(any(PedidoRequest.class)))
                .thenReturn(pedido);

        mockMvc.perform(post("/api/pedidos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.usuarioId").value(1))
                .andExpect(jsonPath("$.sucursalId").value(2))
                .andExpect(jsonPath("$.estado").value("CONFIRMADO"));
    }

    @Test
    void testCambiarEstado() throws Exception {
        Pedido pedido = new Pedido();
        pedido.setUsuarioId(1L);
        pedido.setSucursalId(2L);
        pedido.setProductos(List.of(1L, 2L));
        pedido.setEstado("CANCELADO");

        Mockito.when(pedidoService.cambiarEstado(eq(1L), eq("CANCELADO")))
                .thenReturn(pedido);

        mockMvc.perform(put("/api/pedidos/1/estado")
                .param("nuevoEstado", "CANCELADO"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("CANCELADO"));
    }

    @Test
    void testListarPedidos() throws Exception {
        Pedido pedido = new Pedido();
        pedido.setUsuarioId(1L);
        pedido.setSucursalId(2L);
        pedido.setProductos(List.of(1L, 2L));
        pedido.setEstado("CONFIRMADO");

        Mockito.when(pedidoService.listarTodos())
                .thenReturn(List.of(pedido));

        mockMvc.perform(get("/api/pedidos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].usuarioId").value(1))
                .andExpect(jsonPath("$[0].estado").value("CONFIRMADO"));
    }
}
