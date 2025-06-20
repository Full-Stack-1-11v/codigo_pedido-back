package com.perfulandia.pedidos.dto;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * DTO para representar una alerta de inventario.
 * Contiene información sobre el producto, sucursal, stock actual y mensaje de alerta.
 */
@Data
public class AlertaInventario {

    private Long id;
    private Long productoId;
    private Long sucursalId;
    private int stockActual;
    private String mensaje;
    private LocalDateTime fechaHora;
}