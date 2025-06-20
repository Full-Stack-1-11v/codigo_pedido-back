package com.perfulandia.pedidos.dto;

import java.util.List;

/**
 * DTO para representar una solicitud de creación de pedido.
 * Contiene información sobre el usuario, sucursal, productos y estado del pedido.
 */
public class PedidoRequest {

    private Long usuarioId;
    private Long sucursalId;
    private List<Long> productos;
    private String estado; // ✅ nuevo campo

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
