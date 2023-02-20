package br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.port.output

import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.domain.Client
import java.util.*

interface ClientRepositoryPort {

    fun save(client: Client): Client
    fun update(client: Client): Client
    fun findAllAndActiveTrue(): List<Client>
    fun findByIdAndActiveTrue(clientId: UUID): Client
    fun delete(clientId: UUID)

}