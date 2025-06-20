package com.perfulandia.pedidos.service;

import com.perfulandia.pedidos.dto.PedidoRequest;
import com.perfulandia.pedidos.feign.SucursalClient;
import com.perfulandia.pedidos.feign.UsuarioClient;
import com.perfulandia.pedidos.model.Pedido;
import com.perfulandia.pedidos.repository.PedidoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import com.perfulandia.pedidos.feign.InventarioClient;

class PedidoServiceTest {

    private UsuarioClient usuarioClient;
    private SucursalClient sucursalClient;
    private InventarioClient inventarioClient; // ✅ Añadido
    private PedidoRepository pedidoRepository;
    private PedidoService pedidoService;

    @BeforeEach
    void setUp() {
        usuarioClient = mock(UsuarioClient.class);
        sucursalClient = mock(SucursalClient.class);
        inventarioClient = mock(InventarioClient.class); // ✅ Mockeado correctamente
        pedidoRepository = mock(PedidoRepository.class);
        pedidoService = new PedidoService(
            usuarioClient,
            sucursalClient,
            inventarioClient, // ✅ Pasa el mock al constructor
            pedidoRepository
        );
    }

    @Test
    void testCrearPedido_Exitoso() {
        PedidoRequest request = new PedidoRequest();
        request.setUsuarioId(1L);
        request.setSucursalId(2L);
        request.setProductos(List.of(1L, 2L));

        when(usuarioClient.validarUsuario(1L)).thenReturn(true);
        when(sucursalClient.verificarStock(2L, request.getProductos())).thenReturn(true);
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(new Pedido());

        Pedido resultado = pedidoService.crearPedido(request);

        assertNotNull(resultado);
        verify(usuarioClient).validarUsuario(1L);
        verify(sucursalClient).verificarStock(2L, request.getProductos());
        verify(pedidoRepository).save(any(Pedido.class));
    }

    @Test
    void testCrearPedido_UsuarioNoValido() {
        PedidoRequest request = new PedidoRequest();
        request.setUsuarioId(1L);
        request.setSucursalId(2L);
        request.setProductos(List.of(1L, 2L));

        when(usuarioClient.validarUsuario(1L)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            pedidoService.crearPedido(request);
        });

        assertEquals("Usuario no válido", exception.getMessage());
        verify(usuarioClient).validarUsuario(1L);
    }

    @Test
    void testCrearPedido_StockInsuficiente() {
        PedidoRequest request = new PedidoRequest();
        request.setUsuarioId(1L);
        request.setSucursalId(2L);
        request.setProductos(List.of(1L, 2L));

        when(usuarioClient.validarUsuario(1L)).thenReturn(true);
        when(sucursalClient.verificarStock(2L, request.getProductos())).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            pedidoService.crearPedido(request);
        });

        assertEquals("Stock insuficiente", exception.getMessage());
        verify(usuarioClient).validarUsuario(1L);
        verify(sucursalClient).verificarStock(2L, request.getProductos());
    }

    @Test
    void testCambiarEstado_Exitoso() {
        Pedido pedidoExistente = new Pedido();
        pedidoExistente.setUsuarioId(1L);
        pedidoExistente.setSucursalId(2L);
        pedidoExistente.setProductos(List.of(1L, 2L));
        pedidoExistente.setEstado("CONFIRMADO");

        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedidoExistente));
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedidoExistente);

        Pedido actualizado = pedidoService.cambiarEstado(1L, "CANCELADO");

        assertEquals("CANCELADO", actualizado.getEstado());
        verify(pedidoRepository).findById(1L);
        verify(pedidoRepository).save(any(Pedido.class));
    }

    @Test
    void testCambiarEstado_PedidoNoEncontrado() {
        when(pedidoRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            pedidoService.cambiarEstado(1L, "CANCELADO");
        });

        assertEquals("Pedido no encontrado", exception.getMessage());
        verify(pedidoRepository).findById(1L);
    }

    @Test
    void testListarTodos() {
        Pedido pedido = new Pedido();
        when(pedidoRepository.findAll()).thenReturn(List.of(pedido));

        List<Pedido> result = pedidoService.listarTodos();
        assertEquals(1, result.size());
        verify(pedidoRepository).findAll();
    }
}
