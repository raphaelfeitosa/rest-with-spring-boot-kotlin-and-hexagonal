package br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.converter

import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.api.request.CreateClientRequest
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.api.request.UpdateClientRequest
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.domain.Address
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.domain.Client
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.domain.Client.Document
import java.util.*

fun CreateClientRequest.toDomain() = Client(
    name = this.name!!,
    gender = this.gender!!,
    email = this.email!!,
    document = Document(
        number = this.documentNumber!!,
        type = enumValueOf(this.documentType!!)
    ),
    salary = this.salary!!,
    address = with(this.address!!) {
        Address(
            zipCode = this.zipCode!!,
            number = this.number!!
        )
    }
)

fun UpdateClientRequest.toDomain(clientId: UUID) = Client(
    id = clientId,
    name = this.name!!,
    gender = this.gender!!,
    email = this.email!!,
    document = Document(
        number = this.documentNumber!!,
        type = enumValueOf(this.documentType!!)
    ),
    salary = this.salary!!
)