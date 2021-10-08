package com.crash.br.repository

import com.crash.br.model.Cambio
import org.springframework.data.jpa.repository.JpaRepository

interface CambioRepository: JpaRepository<Cambio, Long> {

    fun findByFromAndTo(from: String, to: String) : Cambio

}