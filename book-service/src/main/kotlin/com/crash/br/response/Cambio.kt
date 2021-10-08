package com.crash.br.response

data class Cambio(
    val from: String,
    val to: String,
    val conversionFactor: Double,
    val convertedValue: Double,
    var environment: String
    ) {
    var id: Long? = null
}