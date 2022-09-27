package br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.service

import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.controller.ClientController
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.domain.Client
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.port.input.UpdateClientUseCase
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.port.output.ClientRepositoryPort
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*

@Service
class UpdateClientService(
    private val clientRepositoryPort: ClientRepositoryPort,
) : UpdateClientUseCase {

    private val logger = LoggerFactory.getLogger(ClientController::class.java.name)

    override fun execute(client: Client): Client {
        logger.info("Starting service to update a client with clientId: [{}]", client.id)
        return client.update(clientRepositoryPort).also {
            logger.info("Done service to update a client: [{}]", it)
        }
    }
}