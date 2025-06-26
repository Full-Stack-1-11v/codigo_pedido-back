package com.perfulandia.pedidos.feign;

import com.perfulandia.pedidos.dto.AlertaInventario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Cliente Feign para interactuar con el microservicio de inventario.
 */
@FeignClient(name = "inventario-client", url = "${inventario.service.url}")
public interface InventarioClient {

    /**
     * Obtiene alertas de inventario por sucursal y producto.
     */
    @GetMapping("/api/alertas/obtener/sucursal/{sucursalId}/producto/{productoId}")
    List<AlertaInventario> listarAlertasPorSucursalYProducto(
            @PathVariable("sucursalId") Long sucursalId,
            @PathVariable("productoId") Long productoId
    );
}
