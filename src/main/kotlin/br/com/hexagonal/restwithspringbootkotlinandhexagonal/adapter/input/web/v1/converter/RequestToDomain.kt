package br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.converter

import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.api.request.CreateClientRequest
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.domain.Address
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.domain.Client
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.domain.Client.Document

fun CreateClientRequest.toDomain() = Client(
    name = this.name!!,
    gender = this.gender!!,
    email = this.email!!,
    document = Document(
        number = this.documentNumber!!,
        type = enumValueOf(this.documentType!!)
    ),
    salary = this.salary!!,
    address = Address(
        zipCode = this.address!!.zipCode!!,
        number = this.address!!.number!!
    )
)