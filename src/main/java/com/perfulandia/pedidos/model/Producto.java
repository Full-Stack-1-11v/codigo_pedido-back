package com.perfulandia.pedidos.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    @Id
    private Long id;
    private String nombre;
    private double precio;
}
