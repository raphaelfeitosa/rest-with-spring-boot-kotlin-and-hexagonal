package br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.output.postgresql.converter

import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.output.postgresql.entity.ClientEntity
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.domain.Client

fun Client.toEntity() = ClientEntity(
    id = this.id,
    name = this.name,
    gender = this.gender,
    email = this.email,
    documentNumber = this.document.number,
    documentType = this.document.type.name,
    salary = this.salary,
    street = this.address.street!!,
    district = this.address.district!!,
    city = this.address.city!!,
    state = this.address.state!!,
    zipCode = this.address.zipCode!!,
    number = this.address.number!!,
    createdAt = this.createdAt
    )
