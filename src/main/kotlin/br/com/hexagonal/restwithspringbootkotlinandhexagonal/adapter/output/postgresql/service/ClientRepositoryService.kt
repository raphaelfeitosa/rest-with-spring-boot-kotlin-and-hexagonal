package br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.output.postgresql.service

import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.controller.ClientController
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.output.feign.viacep.converter.toDomain
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.output.postgresql.converter.toEntity
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.output.postgresql.repository.ClientRepository
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.domain.Client
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.port.output.ClientRepositoryPort
import com.google.gson.GsonBuilder
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service


@Service
class ClientRepositoryService(
    private val clientRepository: ClientRepository
) : ClientRepositoryPort {
    private val logger = LoggerFactory.getLogger(ClientController::class.java.name)
    private val gson = GsonBuilder().setPrettyPrinting().create()


    override fun save(client: Client) {
        logger.info("Starting process to save a client: [{}] in DB.", client)
        clientRepository.save(client.toEntity())
        logger.info("Done process to save a client: [{}]", client)
    }
}