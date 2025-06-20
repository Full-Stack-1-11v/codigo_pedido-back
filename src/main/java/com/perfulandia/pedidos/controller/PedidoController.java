package com.perfulandia.pedidos.controller;

import com.perfulandia.pedidos.dto.AlertaInventario;
import com.perfulandia.pedidos.dto.PedidoRequest;
import com.perfulandia.pedidos.model.Pedido;
import com.perfulandia.pedidos.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
//**
 // */ Controlador REST para gestionar pedidos y alertas de inventario.
 //* Permite crear pedidos, cambiar su estado y listar alertas de inventario por sucursal y producto.
 //
@RestController
@RequestMapping("/api/pedidos")
@Tag(name = "Pedidos", description = "API para gestionar pedidos y alertas de inventario")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    /**
     * Crea un nuevo pedido.
     * @param request Datos del pedido a crear.
     * @return Pedido creado.
     */
    @PostMapping
    @Operation(summary = "Crear un nuevo pedido")
    public ResponseEntity<Pedido> crearPedido(@RequestBody PedidoRequest request) {
        Pedido pedido = pedidoService.crearPedido(request);
        return ResponseEntity.ok(pedido);
    }
/**
     * Cambia el estado de un pedido existente.
     * @param id ID del pedido a actualizar.
     * @param nuevoEstado Nuevo estado del pedido.
     * @return Pedido actualizado.
     */
    @PutMapping("/{id}/estado")
    @Operation(summary = "Cambiar el estado de un pedido")
    public ResponseEntity<Pedido> cambiarEstado(
            @PathVariable Long id,
            @RequestParam String nuevoEstado) {
        Pedido pedido = pedidoService.cambiarEstado(id, nuevoEstado);
        return ResponseEntity.ok(pedido);
    }
/**
     * Lista todos los pedidos existentes.
     * @return Lista de pedidos.
     */
    @GetMapping
    @Operation(summary = "Listar todos los pedidos")
    public ResponseEntity<List<Pedido>> listarPedidos() {
        List<Pedido> pedidos = pedidoService.listarTodos();
        return ResponseEntity.ok(pedidos);
    }

    // ✅ NUEVO ENDPOINT: Listar alertas de inventario por sucursal y producto
    @GetMapping("/alertas/sucursal/{sucursalId}/producto/{productoId}")
    @Operation(summary = "Obtener alertas de inventario por sucursal y producto")
    public ResponseEntity<List<AlertaInventario>> listarAlertasPorSucursalYProducto(
            @PathVariable Long sucursalId,
            @PathVariable Long productoId) {
        List<AlertaInventario> alertas = pedidoService.obtenerAlertasPorSucursalYProducto(sucursalId, productoId);
        return ResponseEntity.ok(alertas);
    }
}