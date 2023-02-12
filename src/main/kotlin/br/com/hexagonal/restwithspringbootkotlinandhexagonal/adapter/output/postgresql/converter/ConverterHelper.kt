package br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.output.postgresql.converter

import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.output.postgresql.entity.AddressEntity
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.output.postgresql.entity.ClientEntity
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.domain.Client.Address
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.domain.Client
import com.fasterxml.jackson.databind.ObjectMapper

fun Client.toEntity() = ClientEntity(
    id = this.id,
    name = this.name,
    gender = this.gender,
    email = this.email,
    documentNumber = this.document.number,
    documentType = this.document.type.name,
    salary = this.salary,
    address = this.address!!.toEntity(),
    additionalInformation = this.additionalInformation!!.objectToJson(),
    createdAt = this.createdAt,
    updatedAt = this.updatedAt
)

private fun Set<Address>.toEntity(): MutableList<AddressEntity> =
    this.map { it.toEntity() }.toMutableList()
private fun Address.toEntity(): AddressEntity =
    AddressEntity(
        id = this.id,
        street = this.street!!,
        district = this.district!!,
        city = this.city!!,
        state = this.state!!,
        zipCode = this.zipCode!!,
        number = this.number!!,
        clientId = this.clientId!!
    )

fun Client.toUpdateEntity(entity: ClientEntity): ClientEntity {
    entity.name = this.name
    entity.gender = this.gender
    entity.email = this.email
    entity.documentNumber = this.document.number
    entity.documentType = this.document.type.name
    entity.salary = this.salary
    entity.additionalInformation = this.additionalInformation!!.objectToJson()
    entity.updatedAt = this.updatedAt

    return entity
}

fun ClientEntity.toDomain() =
    Client(
        id = this.id,
        name = this.name,
        gender = this.gender,
        email = this.email,
        document = Client.Document(
            number = this.documentNumber,
            type = enumValueOf(this.documentType)
        ),
        salary = this.salary,
        address = this.address.toDomain(),
        additionalInformation = this.additionalInformation!!.stringToMap(),
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )

private fun List<AddressEntity>.toDomain(): Set<Address> =
    this.map { it.toDomain() }.toSet()

private fun AddressEntity.toDomain(): Address =
    Address(
        id = this.id,
        street = this.street,
        district = this.district,
        city = this.city,
        state = this.state,
        zipCode = this.zipCode,
        number = this.number
    )

private fun String.stringToMap() =
    ObjectMapper().readValue(this, Map::class.java) as Map<String, String>?

private fun Map<String, String>.objectToJson() = ObjectMapper().writeValueAsString(this)