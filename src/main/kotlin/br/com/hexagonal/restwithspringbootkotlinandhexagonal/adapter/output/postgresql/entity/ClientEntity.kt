package br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.output.postgresql.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "client")
class ClientEntity(

    @Id
    @Column(name = "id")
    var id: UUID? = null,

    @Column(name = "name")
    var name: String? = null,

    @Column(name = "gender")
    var gender: String? = null,

    @Column(name = "email")
    var email: String? = null,

    @Column(name = "document_number")
    var documentNumber: String? = null,

    @Column(name = "document_type")
    var documentType: String? = null,

    @Column(name = "salary")
    var salary: BigDecimal? = null,

    @Column(name = "street")
    var street: String? = null,

    @Column(name = "district")
    var district: String? = null,

    @Column(name = "city")
    var city: String? = null,

    @Column(name = "state")
    var state: String? = null,

    @Column(name = "zip_code")
    var zipCode: String? = null,

    @Column(name = "number")
    var number: String? = null,

    @Column(name = "created_at")
    var createdAt: LocalDateTime? = null,

    @Column(name = "updated_at")
    var updatedAt: LocalDateTime = LocalDateTime.now(),
)