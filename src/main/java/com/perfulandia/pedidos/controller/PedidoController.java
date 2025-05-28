package com.perfulandia.pedidos.controller;


import com.perfulandia.pedidos.model.Pedido;
import com.perfulandia.pedidos.service.PedidoService;
import com.perfulandia.pedidos.feign.UsuarioClient;
import com.perfulandia.pedidos.feign.ProductoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private UsuarioClient usuarioClient;

    @Autowired
    private ProductoClient productoClient;

    @GetMapping
    public List<Pedido> listarPedidos() {
        return pedidoService.listarPedidos();
    }

    @GetMapping("/{id}")
    public Optional<Pedido> obtenerPedido(@PathVariable Long id) {
        return pedidoService.obtenerPedidoPorId(id);
    }

    @PostMapping
    public Pedido crearPedido(@RequestBody Pedido pedido) {
        usuarioClient.getUsuarioById(pedido.getUsuarioId());
        for (Long productoId : pedido.getProductosIds()) {
            productoClient.getProductoById(productoId);
        }
        return pedidoService.guardarPedido(pedido);
    }

    @DeleteMapping("/{id}")
    public void eliminarPedido(@PathVariable Long id) {
        pedidoService.eliminarPedido(id);
    }
}
