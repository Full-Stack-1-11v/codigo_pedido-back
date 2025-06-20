package com.perfulandia.pedidos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO para representar la respuesta de un pedido.
 * Contiene un mensaje y un indicador de éxito.
 */
@Data
@AllArgsConstructor
public class PedidoResponse {
    private String mensaje;
    private boolean exitoso;
}
