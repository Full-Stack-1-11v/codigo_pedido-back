package com.perfulandia.pedidos.dto;

import lombok.Data;

/**
 * DTO para representar un producto en un pedido.
 * Contiene información sobre el ID, nombre y cantidad del producto.
 */
@Data
public class ProductoDTO {
    private Long id;
    private String nombre;
    private int cantidad;
}