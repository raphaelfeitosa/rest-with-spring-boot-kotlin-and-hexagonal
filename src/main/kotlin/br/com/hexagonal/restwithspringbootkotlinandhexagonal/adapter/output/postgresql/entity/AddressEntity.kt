package br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.output.postgresql.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "tb_address")
class AddressEntity(

    @Id
    @Column(name = "id")
    val id: UUID,

    @Column(name = "street")
    val street: String,

    @Column(name = "district")
    val district: String,

    @Column(name = "city")
    val city: String,

    @Column(name = "state")
    val state: String,

    @Column(name = "zip_code")
    val zipCode: String,

    @Column(name = "number")
    val number: String,

    @Column(name = "client_id")
    val clientId: UUID,

    @ManyToOne
    @JoinColumn(name = "client_id", insertable = false, updatable = false)
    @JsonBackReference
    val client: ClientEntity? = null

)