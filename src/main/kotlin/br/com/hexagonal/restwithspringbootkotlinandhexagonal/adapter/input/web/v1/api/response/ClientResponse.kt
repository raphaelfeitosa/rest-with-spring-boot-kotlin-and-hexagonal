package br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.api.response

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class ClientResponse(
    val id: UUID,
    val name: String,
    val gender: String,
    val email: String,
    val document: Document,
    val salary: BigDecimal,
    val address: Address,
    val additionalInformation: Map<String, String>?,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
) {

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class Document(
        val documentNumber: String,
        val documentType: String,
    )

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class Address(
        var street: String,
        var district: String,
        var city: String,
        var state: String,
        val zipCode: String,
        val number: String
    )
}