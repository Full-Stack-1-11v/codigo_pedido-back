package com.perfulandia.pedidos.dto;

import java.time.LocalDateTime;

/**
 * DTO para representar una alerta de inventario.
 * Contiene información sobre el producto, sucursal, stock actual y mensaje de alerta.
 */
public class AlertaInventario {

    private Long id;
    private Long productoId;
    private Long sucursalId;
    private int stockActual;
    private String mensaje;
    private LocalDateTime fechaHora;

    public AlertaInventario() {
    }

    public AlertaInventario(Long id, Long productoId, Long sucursalId, int stockActual, String mensaje, LocalDateTime fechaHora) {
        this.id = id;
        this.productoId = productoId;
        this.sucursalId = sucursalId;
        this.stockActual = stockActual;
        this.mensaje = mensaje;
        this.fechaHora = fechaHora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public Long getSucursalId() {
        return sucursalId;
    }

    public void setSucursalId(Long sucursalId) {
        this.sucursalId = sucursalId;
    }

    public int getStockActual() {
        return stockActual;
    }

    public void setStockActual(int stockActual) {
        this.stockActual = stockActual;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
}
