package br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.controller

import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.api.ClientApi
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.api.request.CreateClientRequest
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.api.request.UpdateClientRequest
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.api.response.ClientResponse
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.converter.toDomain
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.converter.toResponse
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.port.input.CreateClientUseCase
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.port.input.FindClientUseCase
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.port.input.UpdateClientUseCase
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class ClientController(
    private val createClientUseCase: CreateClientUseCase,
    private val updateClientUseCase: UpdateClientUseCase,
    private val findClientUseCase: FindClientUseCase
) : ClientApi {

    private val logger = LoggerFactory.getLogger(ClientController::class.java.name)

    override fun create(createClientRequest: CreateClientRequest): ClientResponse {
        logger.info("Request received to create a new client: [{}]...", createClientRequest)
        return createClientUseCase.execute(
            createClientRequest.toDomain()
        ).also {
            logger.info("Client created: [{}].", it)
        }.toResponse()
    }

    override fun update(clientId: UUID, updateClientRequest: UpdateClientRequest): ClientResponse {
        logger.info(
            "Request received to update a client: [{}] with clientId: [{}]...",
            updateClientRequest, clientId
        )
        return updateClientUseCase.execute(
            client = updateClientRequest.toDomain(clientId)
        ).also {
            logger.info("Client with clientId: [{}] updated.", it.id)
        }.toResponse()
    }

    override fun findById(clientId: UUID): ClientResponse {
        logger.info("Request received to find client with clientId: [{}]...", clientId)
        return findClientUseCase.execute(clientId).also {
            logger.info("Client found: [{}].", it)
        }.toResponse()
    }

}