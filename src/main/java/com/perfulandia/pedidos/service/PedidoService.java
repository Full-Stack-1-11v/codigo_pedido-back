package com.perfulandia.pedidos.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.perfulandia.pedidos.dto.PedidoDTO;
import com.perfulandia.pedidos.feign.ProductoClient;
import com.perfulandia.pedidos.model.Pedido;
import com.perfulandia.pedidos.model.Producto;
import com.perfulandia.pedidos.model.RutaEntrega;
import com.perfulandia.pedidos.repository.PedidoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository repository;
    private final ProductoClient productoClient;

    public Pedido crearPedido(PedidoDTO dto) {
        List<Producto> productos = dto.getProductosIds().stream()
                .map(productoClient::obtenerProducto)
                .collect(Collectors.toList());

        RutaEntrega ruta = new RutaEntrega(null, dto.getEstadoEntrega() != null ? dto.getEstadoEntrega() : "en camino");
        Pedido pedido = new Pedido(null, productos, ruta);

        return repository.save(pedido);
    }

    public Pedido cambiarEstado(Long id, String nuevoEstado) {
        Pedido pedido = repository.findById(id).orElseThrow();
        pedido.getRutaEntrega().setEstado(nuevoEstado);
        return repository.save(pedido);
    }

    public List<Pedido> listarTodos() {
        return repository.findAll();
    }
}