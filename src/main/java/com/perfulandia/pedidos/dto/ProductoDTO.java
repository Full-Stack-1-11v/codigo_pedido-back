package com.perfulandia.pedidos.dto;

/**
 * DTO para representar un producto en un pedido.
 * Contiene información sobre el ID, nombre y cantidad del producto.
 */
public class ProductoDTO {

    private Long id;
    private String nombre;
    private int cantidad;

    public ProductoDTO() {
    }

    public ProductoDTO(Long id, String nombre, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
