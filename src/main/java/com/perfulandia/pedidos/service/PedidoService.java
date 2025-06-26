package com.perfulandia.pedidos.service;

import com.perfulandia.pedidos.dto.AlertaInventario;
import com.perfulandia.pedidos.dto.PedidoRequest;
import com.perfulandia.pedidos.feign.InventarioClient;
import com.perfulandia.pedidos.feign.SucursalClient;
import com.perfulandia.pedidos.feign.UsuarioClient;
import com.perfulandia.pedidos.model.Pedido;
import com.perfulandia.pedidos.repository.PedidoRepository;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio para manejar la lógica de negocio relacionada con los pedidos.
 * Incluye la creación de pedidos, cambio de estado y obtención de alertas de inventario.
 */
@Service
public class PedidoService {

    private final UsuarioClient usuarioClient;
    private final SucursalClient sucursalClient;
    private final InventarioClient inventarioClient;
    private final PedidoRepository pedidoRepository;

    /**
     * Constructor con inyección de dependencias.
     * Spring detecta automáticamente el único constructor y lo usa para inyectar los beans.
     *
     * @param usuarioClient     Cliente Feign para validar usuarios.
     * @param sucursalClient    Cliente Feign para verificar stock en sucursales.
     * @param inventarioClient  Cliente Feign para obtener alertas de inventario.
     * @param pedidoRepository  Repositorio JPA para persistencia de pedidos.
     */
    public PedidoService(
            UsuarioClient usuarioClient,
            SucursalClient sucursalClient,
            InventarioClient inventarioClient,
            PedidoRepository pedidoRepository) {
        this.usuarioClient = usuarioClient;
        this.sucursalClient = sucursalClient;
        this.inventarioClient = inventarioClient;
        this.pedidoRepository = pedidoRepository;
    }

    /**
     * Crea un nuevo pedido validando el usuario y verificando el stock.
     *
     * @param request DTO con datos del pedido.
     * @return Pedido creado.
     */
    public Pedido crearPedido(PedidoRequest request) {
        if (!usuarioClient.validarUsuario(request.getUsuarioId())) {
            throw new RuntimeException("Usuario no válido");
        }

        if (!sucursalClient.verificarStock(request.getSucursalId(), request.getProductos())) {
            throw new RuntimeException("Stock insuficiente");
        }

        Pedido pedido = new Pedido();
        pedido.setUsuarioId(request.getUsuarioId());
        pedido.setSucursalId(request.getSucursalId());
        pedido.setProductos(request.getProductos());
        pedido.setEstado(request.getEstado() != null ? request.getEstado() : "CONFIRMADO");

        return pedidoRepository.save(pedido);
    }

    /**
     * Cambia el estado de un pedido existente.
     *
     * @param id          ID del pedido.
     * @param nuevoEstado Nuevo estado a asignar.
     * @return Pedido actualizado.
     */
    public Pedido cambiarEstado(Long id, String nuevoEstado) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        pedido.setEstado(nuevoEstado);
        return pedidoRepository.save(pedido);
    }

    /**
     * Lista todos los pedidos almacenados.
     *
     * @return Lista de pedidos.
     */
    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    /**
     * Obtiene alertas de inventario para una sucursal y producto específicos.
     *
     * @param sucursalId ID de la sucursal.
     * @param productoId ID del producto.
     * @return Lista de alertas de inventario.
     */
    public List<AlertaInventario> obtenerAlertasPorSucursalYProducto(Long sucursalId, Long productoId) {
        return inventarioClient.listarAlertasPorSucursalYProducto(sucursalId, productoId);
    }
}