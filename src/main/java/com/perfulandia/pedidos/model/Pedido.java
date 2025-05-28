package com.perfulandia.pedidos.model;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pedidos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long usuarioId;

    private LocalDate fechaPedido;

    @ElementCollection
    private List<Long> productosIds;

    private int cantidadTotal;

    private String estado; // PENDIENTE, CONFIRMADO, ENVIADO, etc.
}
