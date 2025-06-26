package com.perfulandia.pedidos.controller;

import com.perfulandia.pedidos.dto.AlertaInventario;
import com.perfulandia.pedidos.dto.PedidoRequest;
import com.perfulandia.pedidos.model.Pedido;
import com.perfulandia.pedidos.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * Controlador REST para gestionar pedidos y alertas de inventario.
 * Permite crear pedidos, cambiar su estado y listar alertas de inventario por sucursal y producto.
 */
@RestController
@RequestMapping("/api/pedidos")
@Tag(name = "Pedidos", description = "API para gestionar pedidos y alertas de inventario")
public class PedidoController {

    private final PedidoService pedidoService;
    private static final Logger logger = Logger.getLogger(PedidoController.class.getName());

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
        logger.info("Creando nuevo pedido para usuario ID: " + request.getUsuarioId());
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
        logger.info("Cambiando estado del pedido ID: " + id + " a: " + nuevoEstado);
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
        logger.info("Listando pedidos: cantidad encontrada = " + pedidos.size());
        return ResponseEntity.ok(pedidos != null ? pedidos : Collections.emptyList());
    }



    /**
     * Obtiene alertas de inventario por sucursal y producto.
     * @param sucursalId ID de la sucursal.
     * @param productoId ID del producto.
     * @return Lista de alertas de inventario.
     */
    @GetMapping("/alertas/sucursal/{sucursalId}/producto/{productoId}")
    @Operation(summary = "Obtener alertas de inventario por sucursal y producto")
    public ResponseEntity<List<AlertaInventario>> listarAlertasPorSucursalYProducto(
            @PathVariable Long sucursalId,
            @PathVariable Long productoId) {
        List<AlertaInventario> alertas = pedidoService.obtenerAlertasPorSucursalYProducto(sucursalId, productoId);
        logger.info("Alertas encontradas para sucursal " + sucursalId + " y producto " + productoId + ": " + alertas.size());
        return ResponseEntity.ok(alertas != null ? alertas : Collections.emptyList());
    }
}
