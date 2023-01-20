package br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.converter

import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.api.response.ClientResponse
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.api.response.ClientResponse.Document
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.domain.Client


fun Client.toResponse() = ClientResponse(
    id = this.id,
    name = this.name,
    gender = this.gender,
    email = this.email,
    document = with(this.document) {
        Document(
            documentNumber = this.number,
            documentType = this.type.name
        )
    },
    salary = this.salary,
    address = with(this.address!!) {
        ClientResponse.Address(
            street = this.street!!,
            district = this.district!!,
            city = this.city!!,
            state = this.state!!,
            zipCode = this.zipCode!!,
            number = this.number!!,
        )
    },
    additionalInformation = this.additionalInformation,
    createdAt = this.createdAt,
    updatedAt = this.updatedAt
)

fun List<Client>.toResponse(): List<ClientResponse> =
    this.map { it.toResponse() }