package com.perfulandia.pedidos.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@TestConfiguration
@EnableFeignClients(basePackages = "com.perfulandia.pedidos.client")
public class TestFeignConfig {
}
