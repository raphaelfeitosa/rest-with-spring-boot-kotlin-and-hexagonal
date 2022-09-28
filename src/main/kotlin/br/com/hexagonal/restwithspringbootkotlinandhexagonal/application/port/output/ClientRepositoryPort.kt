package br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.port.output

import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.domain.Client
import java.util.*

interface ClientRepositoryPort {

    fun save(client: Client): Client
    fun update(client: Client): Client
    fun findAll(): List<Client>
    fun findById(clientId: UUID): Client

}