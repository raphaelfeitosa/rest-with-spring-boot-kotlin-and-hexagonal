package br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.output.postgresql.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "tb_clients")
class ClientEntity(

    @Id
    @Column(name = "id")
    var id: UUID,

    @Column(name = "name")
    var name: String,

    @Column(name = "gender")
    var gender: String,

    @Column(name = "email")
    var email: String,

    @Column(name = "document_number")
    var documentNumber: String,

    @Column(name = "document_type")
    var documentType: String,

    @Column(name = "salary")
    var salary: BigDecimal,

    @Column(name = "street")
    var street: String,

    @Column(name = "district")
    var district: String,

    @Column(name = "city")
    var city: String,

    @Column(name = "state")
    var state: String,

    @Column(name = "zip_code")
    var zipCode: String,

    @Column(name = "number")
    var number: String,

    @Column(name = "additional_information")
    var additionalInformation: String?,

    @Column(name = "created_at")
    var createdAt: LocalDateTime,

    @Column(name = "updated_at")
    var updatedAt: LocalDateTime? = LocalDateTime.now()

)