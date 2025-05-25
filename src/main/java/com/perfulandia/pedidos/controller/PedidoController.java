package com.perfulandia.pedidos.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.perfulandia.pedidos.dto.PedidoDTO;
import com.perfulandia.pedidos.model.Pedido;
import com.perfulandia.pedidos.service.PedidoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService servicio;

    @PostMapping
    public Pedido crear(@RequestBody PedidoDTO dto) {
        return servicio.crearPedido(dto);
    }

    @PutMapping("/{id}/estado")
    public Pedido actualizarEstado(@PathVariable Long id, @RequestParam String estado) {
        return servicio.cambiarEstado(id, estado);
    }

    @GetMapping
    public List<Pedido> listar() {
        return servicio.listarTodos();
    }
}