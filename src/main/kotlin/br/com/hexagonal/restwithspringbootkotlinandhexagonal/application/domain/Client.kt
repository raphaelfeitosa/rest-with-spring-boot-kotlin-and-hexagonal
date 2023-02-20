package br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.domain

import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.port.output.ClientRepositoryPort
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.domain.Address as AddressIntegration
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

data class Client(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val gender: String,
    val email: String,
    val document: Document,
    val salary: BigDecimal,
    var address: Set<Address>? = null,
    val additionalInformation: Map<String, String>?,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now(),
) {
    data class Document(
        val number: String,
        val type: Type,
    ) {
        enum class Type(val code: String) {
            CPF("F"),
            CNPJ("J")
        }
    }

    data class Address(
        val id: UUID = UUID.randomUUID(),
        var street: String? = null,
        var district: String? = null,
        var city: String? = null,
        var state: String? = null,
        val zipCode: String? = null,
        val number: String? = null,
        var clientId: UUID? = null,
    ) {
        fun setAddress(
            address: AddressIntegration,
            clientId: UUID,
        ) {
            this.street = address.street
            this.district = address.district
            this.city = address.city
            this.state = address.state
            this.clientId = clientId
        }

    }

    fun save(clientRepositoryPort: ClientRepositoryPort) =
        clientRepositoryPort.save(this)

    fun update(clientRepositoryPort: ClientRepositoryPort) =
        clientRepositoryPort.update(this)


}

