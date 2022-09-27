package br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.domain

import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.port.output.ClientRepositoryPort
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

data class Client(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val gender: String,
    val email: String,
    val document: Document,
    val salary: BigDecimal,
    val address: Address? = null,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
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

    fun save(clientRepositoryPort: ClientRepositoryPort) =
        clientRepositoryPort.save(this)

    fun update(clientRepositoryPort: ClientRepositoryPort) =
        clientRepositoryPort.update(this)

}

