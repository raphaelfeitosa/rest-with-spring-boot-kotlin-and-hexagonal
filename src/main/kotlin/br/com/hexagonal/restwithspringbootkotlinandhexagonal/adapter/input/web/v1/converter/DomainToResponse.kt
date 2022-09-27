package br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.converter

import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.api.response.ClientResponse
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.api.response.ClientResponse.Document
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.domain.Client


fun Client.toResponse() = ClientResponse(
    id = this.id,
    name = this.name,
    gender = this.gender,
    email = this.email,
    document = Document(
        documentNumber = this.document.number,
        documentType = this.document.type.name
    ),
    salary = this.salary,
    address = ClientResponse.Address(
        street = this.address!!.street!!,
        district = this.address.district!!,
        city = this.address.city!!,
        state = this.address.state!!,
        zipCode = this.address.zipCode!!,
        number = this.address.number!!,
    )
)