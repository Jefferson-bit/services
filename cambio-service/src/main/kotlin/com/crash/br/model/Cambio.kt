package com.crash.br.model

import java.math.BigDecimal
import javax.persistence.*

@Entity
data class Cambio(
    @Column(name = "from_currency", nullable = false, length = 3)
    val from: String,

    @Column(name = "to_currency", nullable = false, length = 3)
    val to: String,

    @Column(nullable = false)
    val conversionFactor: BigDecimal,

    @Transient
    val convertedValue: BigDecimal,

    @Transient
    var environment: String
    ) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

}