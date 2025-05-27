package com.perfulandia.pedidos.controller;

import com.perfulandia.pedidos.dto.PedidoDTO;
import com.perfulandia.pedidos.model.Pedido;
import com.perfulandia.pedidos.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@RequestBody PedidoDTO dto) {
        Pedido pedido = pedidoService.crearPedido(dto);
        return ResponseEntity.ok(pedido);
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Pedido> cambiarEstado(@PathVariable Long id, @RequestParam String nuevoEstado) {
        Pedido pedido = pedidoService.cambiarEstado(id, nuevoEstado);
        return ResponseEntity.ok(pedido);
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> listarPedidos() {
        List<Pedido> pedidos = pedidoService.listarTodos();
        return ResponseEntity.ok(pedidos);
    }
}
