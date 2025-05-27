package com.perfulandia.pedidos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Guardamos solo los IDs de productos para simplicidad
    @ElementCollection
    private List<Long> productosIds;

    private Long usuarioId;

    private String estadoEntrega;
}
