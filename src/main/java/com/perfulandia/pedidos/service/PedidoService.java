package com.perfulandia.pedidos.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.perfulandia.pedidos.dto.PedidoDTO;
import com.perfulandia.pedidos.dto.ProductoDTO;
import com.perfulandia.pedidos.dto.UsuarioDTO;
import com.perfulandia.pedidos.feign.ProductoClient;
import com.perfulandia.pedidos.feign.UsuarioClient;
import com.perfulandia.pedidos.model.Pedido;
import com.perfulandia.pedidos.repository.PedidoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProductoClient productoClient;
    private final UsuarioClient usuarioClient;

    public Pedido crearPedido(PedidoDTO dto) {
        // Validar usuario
        UsuarioDTO usuario = usuarioClient.obtenerUsuario(dto.getUsuarioId());
        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado: " + dto.getUsuarioId());
        }

        // Validar productos y obtener info
        List<ProductoDTO> productos = dto.getProductosIds().stream()
                .map(productoClient::obtenerProducto)
                .collect(Collectors.toList());

        if (productos.contains(null)) {
            throw new RuntimeException("Algún producto no fue encontrado");
        }

        Pedido pedido = new Pedido();
        pedido.setUsuarioId(dto.getUsuarioId());
        pedido.setProductosIds(dto.getProductosIds());
        pedido.setEstadoEntrega(dto.getEstadoEntrega() != null ? dto.getEstadoEntrega() : "en camino");

        return pedidoRepository.save(pedido);
    }

    public Pedido cambiarEstado(Long id, String nuevoEstado) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado: " + id));
        pedido.setEstadoEntrega(nuevoEstado);
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }
}
