package com.crash.br.controller

import com.crash.br.model.Cambio
import com.crash.br.repository.CambioRepository
import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.lang.RuntimeException
import java.math.BigDecimal
import java.math.RoundingMode

@RestController
@RequestMapping(value = ["/cambio-services"])
class CambioController(private val environment: Environment, private val cambioRepository: CambioRepository) {


    @GetMapping(value = ["/{amount}/{from}/{to}"])
    fun getCambio(
        @PathVariable("amount") amount: BigDecimal,
        @PathVariable("from") from: String,
        @PathVariable("to") to: String
    ): Cambio {
        val cambio: Cambio = cambioRepository.findByFromAndTo(from, to)
        if (cambio == null) throw RuntimeException("Curent Unsupported")

        val conversionFactor = cambio.conversionFactor
        val convertedValue  = conversionFactor.multiply(amount)
        val port = environment.getProperty("local.server.port")
        return Cambio(
            from,
            to,
            conversionFactor,
            convertedValue.setScale(2, RoundingMode.CEILING),
            port!!
        )
    }
}