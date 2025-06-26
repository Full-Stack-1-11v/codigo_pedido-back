package com.perfulandia.pedidos.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Cliente Feign para el microservicio de sucursales.
 */
@FeignClient(name = "sucursal-client", url = "${sucursal.service.url}")
public interface SucursalClient {

    /**
     * Verifica stock en una sucursal.
     */
    @PostMapping("/verificar-stock")
    boolean verificarStock(
            @RequestParam("sucursalId") Long sucursalId,
            @RequestParam("productos") List<Long> productos
    );
}
