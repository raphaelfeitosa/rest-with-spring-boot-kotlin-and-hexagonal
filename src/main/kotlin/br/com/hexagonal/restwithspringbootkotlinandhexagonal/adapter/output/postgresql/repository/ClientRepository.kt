package br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.output.postgresql.repository

import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.output.postgresql.entity.ClientEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*
import kotlin.collections.List

@Repository
interface ClientRepository : JpaRepository<ClientEntity, UUID> {

    fun findByIdAndActiveTrue(clientId: UUID): Optional<ClientEntity>

    @Query(
            """select c from ClientEntity c 
                left join fetch c.address address
                where c.active = true""")
    fun findAllAndActiveTrue(): List<ClientEntity>

    fun findByDocumentNumberAndActiveTrue(documentNumber: String): Optional<ClientEntity>

}