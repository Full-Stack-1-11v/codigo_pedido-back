package com.perfulandia.pedidos.dto;

import lombok.Data;

import java.util.List;

@Data
public class PedidoDTO {
    private Long usuarioId;
    private List<Long> productosIds;
    private String estadoEntrega;
}
