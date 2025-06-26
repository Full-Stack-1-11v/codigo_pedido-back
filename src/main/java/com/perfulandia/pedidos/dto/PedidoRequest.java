package com.perfulandia.pedidos.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 * DTO para la creación de pedidos.
 * Contiene información del usuario, sucursal, productos seleccionados y estado opcional.
 */
public class PedidoRequest {

    @NotNull(message = "El ID del usuario no puede ser nulo")
    private Long usuarioId;

    @NotNull(message = "El ID de la sucursal no puede ser nulo")
    private Long sucursalId;

    @NotEmpty(message = "La lista de productos no puede estar vacía")
    private List<Long> productos;

    // ✅ Campo adicional para establecer estado (opcional)
    private String estado;

    public PedidoRequest() {
    }

    public PedidoRequest(Long usuarioId, Long sucursalId, List<Long> productos) {
        this.usuarioId = usuarioId;
        this.sucursalId = sucursalId;
        this.productos = productos;
    }

    public PedidoRequest(Long usuarioId, Long sucursalId, List<Long> productos, String estado) {
        this.usuarioId = usuarioId;
        this.sucursalId = sucursalId;
        this.productos = productos;
        this.estado = estado;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getSucursalId() {
        return sucursalId;
    }

    public void setSucursalId(Long sucursalId) {
        this.sucursalId = sucursalId;
    }

    public List<Long> getProductos() {
        return productos;
    }

    public void setProductos(List<Long> productos) {
        this.productos = productos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
