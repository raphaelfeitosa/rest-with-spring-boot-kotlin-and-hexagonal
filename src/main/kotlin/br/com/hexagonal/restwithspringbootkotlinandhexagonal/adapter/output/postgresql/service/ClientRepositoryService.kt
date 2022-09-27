package br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.output.postgresql.service

import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.api.errors.Errors.RESOURCE_NOT_FOUND
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.api.exceptions.NotFoundException
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.controller.ClientController
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.output.postgresql.converter.toDomain
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.output.postgresql.converter.toEntity
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.output.postgresql.converter.toUpdateEntity
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.output.postgresql.entity.ClientEntity
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.output.postgresql.repository.ClientRepository
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.domain.Client
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.port.output.ClientRepositoryPort
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*


@Service
class ClientRepositoryService(
    private val clientRepository: ClientRepository,
) : ClientRepositoryPort {

    private val logger = LoggerFactory.getLogger(ClientController::class.java.name)

    override fun save(client: Client): Client {
        logger.info("Starting process to save a client: [{}] in DB.", client)
        return clientRepository.save(
            client.toEntity()
        ).also {
            logger.info("Done process to save a client: [{}]", it)
        }.toDomain()

    }

    override fun update(client: Client): Client {
        logger.info("Starting process to update a client with clientId: [{}] in DB.", client.id)
        val entity = getClientById(client.id)

        return clientRepository.save(
            client.toUpdateEntity(entity)
        ).also {
            logger.info("Done process to update a client: [{}]", it)
        }.toDomain()
    }

    private fun getClientById(clientId: UUID): ClientEntity =
        clientRepository.findById(clientId)
            .orElseThrow { NotFoundException(RESOURCE_NOT_FOUND) }
}