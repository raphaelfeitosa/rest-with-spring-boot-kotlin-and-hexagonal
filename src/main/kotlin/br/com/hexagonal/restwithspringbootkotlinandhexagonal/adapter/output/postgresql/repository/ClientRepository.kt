package br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.output.postgresql.repository

import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.output.postgresql.entity.ClientEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ClientRepository : JpaRepository<ClientEntity, UUID> {

    fun findByDocumentNumber(documentNumber: String): Optional<ClientEntity>

}