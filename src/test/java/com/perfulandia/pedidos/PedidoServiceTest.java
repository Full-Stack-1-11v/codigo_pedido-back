package com.perfulandia.pedidos;

import com.perfulandia.pedidos.dto.PedidoRequest;
import com.perfulandia.pedidos.feign.SucursalClient;
import com.perfulandia.pedidos.feign.UsuarioClient;
import com.perfulandia.pedidos.model.Pedido;
import com.perfulandia.pedidos.repository.PedidoRepository;
import com.perfulandia.pedidos.service.PedidoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

class PedidoServiceTest {

    private UsuarioClient usuarioClient;
    private SucursalClient sucursalClient;
    private PedidoRepository pedidoRepository;
    private PedidoService pedidoService;

    @BeforeEach
    void setUp() {
        usuarioClient = mock(UsuarioClient.class);
        sucursalClient = mock(SucursalClient.class);
        pedidoRepository = mock(PedidoRepository.class);
        pedidoService = new PedidoService(usuarioClient, sucursalClient, pedidoRepository);
    }

    @Test
    void testCrearPedido_Exitoso() {
        PedidoRequest request = new PedidoRequest();
        request.setUsuarioId(1L);
        request.setSucursalId(2L);
        request.setProductos(List.of(100L, 101L));

        when(usuarioClient.validarUsuario(1L)).thenReturn(true);
        when(sucursalClient.verificarStock(2L, request.getProductos())).thenReturn(true);
        when(pedidoRepository.save(any(Pedido.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Pedido resultado = pedidoService.crearPedido(request);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getUsuarioId());
        assertEquals(2L, resultado.getSucursalId());
        assertEquals("CONFIRMADO", resultado.getEstado());
        verify(usuarioClient).validarUsuario(1L);
        verify(sucursalClient).verificarStock(2L, request.getProductos());
        verify(pedidoRepository).save(any(Pedido.class));
    }

    @Test
    void testCrearPedido_UsuarioInvalido() {
        PedidoRequest request = new PedidoRequest();
        request.setUsuarioId(99L);
        request.setSucursalId(2L);
        request.setProductos(List.of(100L));

        when(usuarioClient.validarUsuario(99L)).thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> pedidoService.crearPedido(request));
        assertEquals("Usuario no válido", ex.getMessage());
        verify(usuarioClient).validarUsuario(99L);
        verifyNoInteractions(sucursalClient);
        verifyNoInteractions(pedidoRepository);
    }

    @Test
    void testCrearPedido_StockInsuficiente() {
        PedidoRequest request = new PedidoRequest();
        request.setUsuarioId(1L);
        request.setSucursalId(2L);
        request.setProductos(List.of(100L));

        when(usuarioClient.validarUsuario(1L)).thenReturn(true);
        when(sucursalClient.verificarStock(2L, request.getProductos())).thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> pedidoService.crearPedido(request));
        assertEquals("Stock insuficiente", ex.getMessage());
        verify(usuarioClient).validarUsuario(1L);
        verify(sucursalClient).verificarStock(2L, request.getProductos());
        verifyNoInteractions(pedidoRepository);
    }

    @Test
    void testCambiarEstado_Exitoso() {
        Pedido pedidoExistente = new Pedido();
        pedidoExistente.setUsuarioId(1L);
        pedidoExistente.setSucursalId(2L);
        pedidoExistente.setProductos(List.of(100L));
        pedidoExistente.setEstado("CONFIRMADO");

        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedidoExistente));
        when(pedidoRepository.save(any(Pedido.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Pedido actualizado = pedidoService.cambiarEstado(1L, "CANCELADO");

        assertEquals("CANCELADO", actualizado.getEstado());
        verify(pedidoRepository).findById(1L);
        verify(pedidoRepository).save(any(Pedido.class));
    }

    @Test
    void testCambiarEstado_PedidoNoEncontrado() {
        when(pedidoRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> pedidoService.cambiarEstado(1L, "CANCELADO"));
        assertEquals("Pedido no encontrado", ex.getMessage());
        verify(pedidoRepository).findById(1L);
        verifyNoMoreInteractions(pedidoRepository);
    }
}
