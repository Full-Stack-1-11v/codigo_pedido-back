package com.perfulandia.pedidos.dto;

/**
 * DTO para representar la respuesta de un pedido.
 * Contiene un mensaje y un indicador de éxito.
 */
public class PedidoResponse {
    private String mensaje;
    private boolean exitoso;

    public PedidoResponse() {
    }

    public PedidoResponse(String mensaje, boolean exitoso) {
        this.mensaje = mensaje;
        this.exitoso = exitoso;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isExitoso() {
        return exitoso;
    }

    public void setExitoso(boolean exitoso) {
        this.exitoso = exitoso;
    }
}
