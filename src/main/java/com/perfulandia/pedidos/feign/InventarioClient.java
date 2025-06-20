package com.perfulandia.pedidos.feign;

import com.perfulandia.pedidos.dto.AlertaInventario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Cliente Feign para interactuar con el microservicio de inventario.
 * Permite obtener alertas de inventario por sucursal y producto.
 */
@FeignClient(name = "inventario-client", url = "http://localhost:8082") // Cambia puerto según tu microservicio
public interface InventarioClient {

    /**
     * Obtiene una lista de alertas de inventario para una sucursal y producto específicos.
     *
     * @param sucursalId ID de la sucursal.
     * @param productoId ID del producto.
     * @return Lista de alertas de inventario.
     */
    @GetMapping("/api/alertas/obtener/sucursal/{sucursalId}/producto/{productoId}")
    List<AlertaInventario> listarAlertasPorSucursalYProducto(
            @PathVariable("sucursalId") Long sucursalId,
            @PathVariable("productoId") Long productoId
    );
}