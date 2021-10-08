package com.crash.br.model

import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDate
import javax.persistence.*

@Entity
data class Book(

    @Column(nullable = false, length = 180)
    val author: String,

    @Column(nullable = false, name = "launch_date")
    val launchDate: LocalDate,

    @Column(nullable = false)
    var price: Double,

    @Column(nullable = false)
    val title: String,

    @Transient
    @JsonIgnore
    val currency: String,

    @Transient
    var environment: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}