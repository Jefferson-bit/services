package com.crash.br.controller

import com.crash.br.feignClient.CambioClient
import com.crash.br.model.Book
import com.crash.br.repository.BookRepositories
import com.crash.br.response.Cambio
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping(value = ["book-services"])
class BookController(
    private val environment: Environment,
    private val bookRepository: BookRepositories,
    private val cambioClient: CambioClient
) {


    @GetMapping(value = ["/{id}/{currency}"])
    fun getBook(@PathVariable("id") id: Long, @PathVariable("currency") currency: String): Book{

        val book = bookRepository.getById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
//        val params = mutableMapOf("to" to currency, "amount" to book.price.toString(), "from" to "USD")
//
//        val response = RestTemplate().getForEntity(
//            "http://localhost:8000/cambio-services/{amount}/{from}/{to}",
//            Cambio::class.java, params)

        val response = cambioClient.getCambio(book.price, "USD", currency)
        val port = environment.getProperty("local.server.port")

        book.price = response.convertedValue
        book.environment = port + "feign"
        return book
    }
}