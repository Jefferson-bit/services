package com.crash.br.configuration

import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApiGatewayConfiguration {

    @Bean
    fun gatewayRouter(builder: RouteLocatorBuilder) : RouteLocator{
        return  builder.routes()
            .route { p -> p.path("/get")
                .filters { f -> f
                    .addRequestHeader("Hello", "World")
                    .addRequestParameter("Hello", "World")}
                .uri("http://httpbin.org:80") }
            .route { p -> p.path("/cambio-services/**")
                .uri("lb://cambio-service") }
            .route { p -> p.path("/book-services/**")
                .uri("lb://book-service") }
            .build()
    }
}