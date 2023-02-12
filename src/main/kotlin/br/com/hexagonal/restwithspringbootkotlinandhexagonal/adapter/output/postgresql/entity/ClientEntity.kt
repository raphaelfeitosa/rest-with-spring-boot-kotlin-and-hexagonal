package br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.output.postgresql.entity

import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*
import jakarta.persistence.CascadeType.ALL
import jakarta.persistence.CascadeType.REMOVE
import jakarta.persistence.FetchType.EAGER
import jakarta.persistence.FetchType.LAZY
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "tb_clients")
class ClientEntity(

    @Id
    @Column(name = "id")
    val id: UUID,

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

    @Column(name = "additional_information")
    var additionalInformation: String?,

    @OneToMany(
        fetch = LAZY,
        cascade = [ALL],
        orphanRemoval = true
    )
    @JoinColumn(name= "client_id")
    @JsonManagedReference
    var address: List<AddressEntity> = listOf(),

    @Column(name = "created_at")
    val createdAt: LocalDateTime,

    @Column(name = "updated_at")
    var updatedAt: LocalDateTime = LocalDateTime.now()

)