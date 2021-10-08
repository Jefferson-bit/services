package com.crash.br.feignClient

import com.crash.br.response.Cambio
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(url = "\${cambio.url}", name = "cambio-service")
interface CambioClient {
    @GetMapping(value = ["/cambio-services/{amount}/{from}/{to}"])
    fun getCambio(
        @PathVariable("amount") amount: Double,
        @PathVariable("from") from: String,
        @PathVariable("to") to: String
    ): Cambio
}