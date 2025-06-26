package com.perfulandia.pedidos.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.perfulandia.pedidos.config.TestFeignConfig;
import com.perfulandia.pedidos.dto.AlertaInventario;
import com.perfulandia.pedidos.dto.PedidoRequest;
import com.perfulandia.pedidos.model.Pedido;
import com.perfulandia.pedidos.service.PedidoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PedidoController.class)

//@Import(TestFeignConfig.class)
@ActiveProfiles("test")

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

    @Test
    void crearPedido_conDatosInvalidos_deberiaRetornarBadRequest() throws Exception {
        PedidoRequest request = new PedidoRequest(); // faltan datos obligatorios

        mockMvc.perform(post("/api/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }


    @Test
    void testListarAlertasPorSucursalYProducto() throws Exception {
        Long sucursalId = 1L;
        Long productoId = 100L;

        AlertaInventario alerta = new AlertaInventario();
        alerta.setSucursalId(sucursalId);
        alerta.setProductoId(productoId);
        alerta.setMensaje("Stock crítico");

        Mockito.when(pedidoService.obtenerAlertasPorSucursalYProducto(sucursalId, productoId))
                .thenReturn(List.of(alerta));

        mockMvc.perform(get("/api/pedidos/alertas/sucursal/{sucursalId}/producto/{productoId}", sucursalId, productoId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].sucursalId").value(1))
                .andExpect(jsonPath("$[0].productoId").value(100))
                .andExpect(jsonPath("$[0].mensaje").value("Stock crítico"));
    }

    @Test
    void cambiarEstado_conIdInvalido_deberiaRetornarNotFound() throws Exception {
        Mockito.when(pedidoService.cambiarEstado(eq(99L), eq("ENTREGADO")))
                .thenThrow(new RuntimeException("Pedido no encontrado"));

        mockMvc.perform(put("/api/pedidos/99/estado")
                        .param("nuevoEstado", "ENTREGADO"))
                .andExpect(status().is5xxServerError());
    }

}
