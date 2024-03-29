package br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.output.postgresql.service

import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.api.errors.Errors.RESOURCE_NOT_FOUND
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.api.exceptions.NotFoundException
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
    private val clientRepository: ClientRepository
) : ClientRepositoryPort {

    companion object {
        private val logger = LoggerFactory.getLogger(ClientRepositoryService::class.java.name)
    }

    override fun save(client: Client): Client {
        logger.info("Starting process to save a client: [{}], in DB.", client)
        val clientEntity = client.toEntity()
        clientRepository.save(clientEntity)
        logger.info("Done process to save a client: [{}], in DB", client)
        return clientEntity.toDomain()
    }

    override fun update(client: Client): Client {
        logger.info("Starting process to update a client with clientId: [{}], in DB.", client.id)
        val clientEntity = getClientById(client.id)
        clientRepository.save(client.toUpdateEntity(clientEntity))
        logger.info("Done process to update a client: [{}], in DB", client)
        return clientEntity.toDomain()
    }

    override fun findAllAndActiveTrue(): List<Client> {
        logger.info("Starting process to find find all clients in DB.")
        return clientRepository.findAllAndActiveTrue()
            .map { it.toDomain() }
            .also {
                logger.info("Done process to find all clients in DB.")
            }
    }

    override fun findByIdAndActiveTrue(clientId: UUID): Client {
        logger.info("Starting process to find a client with clientId: [{}], in DB.", clientId)
        return getClientById(clientId)
            .toDomain()
            .also {
                logger.info("Done process to find a client: [{}], in DB", it)
            }
    }

    override fun delete(clientId: UUID) {
        logger.info("Starting process to delete a client with clientId: [{}], in DB.", clientId)
        val entity = getClientById(clientId)

        entity.active = false
        entity.address.parallelStream().forEach {
            it.active = false
        }

        clientRepository.save(entity)
        logger.info("Done process to delete a client: [{}], in DB", entity)

    }

    private fun getClientById(clientId: UUID): ClientEntity =
        clientRepository.findByIdAndActiveTrue(clientId).map { it }
            .orElseThrow { NotFoundException(RESOURCE_NOT_FOUND) }

}