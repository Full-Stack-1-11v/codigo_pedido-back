package com.perfulandia.pedidos.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Cliente Feign para interactuar con el microservicio de sucursales.
 * Permite verificar el stock de productos en una sucursal específica.
 */
@FeignClient(
    name = "sucursal-client",
    url = "http://localhost:8082/api/sucursales"
)
public interface SucursalClient {
    /**
     * Verifica el stock de productos en una sucursal específica.
     *
     * @param sucursalId ID de la sucursal a verificar.
     * @param productos Lista de IDs de productos a verificar.
     * @return true si hay stock disponible, false en caso contrario.
     */
    @PostMapping("/verificar-stock")
    boolean verificarStock(
        @RequestParam("sucursalId") Long sucursalId,
        @RequestParam("productos") List<Long> productos
    );
}
