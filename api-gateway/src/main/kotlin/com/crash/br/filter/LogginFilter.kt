package com.crash.br.filter

import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.GlobalFilter
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class LogginFilter: GlobalFilter {

    private val logger = LoggerFactory.getLogger(LogginFilter::class.java)

    override fun filter(exchange: ServerWebExchange?, chain: GatewayFilterChain?): Mono<Void> {
        logger.info("ORIGINAL REQUEST PATH: {}", exchange!!.request.path)
        return chain!!.filter(exchange)
    }
}