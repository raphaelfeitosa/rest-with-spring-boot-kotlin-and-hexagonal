package br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.controller

import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.api.ClientApi
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.api.request.CreateClientRequest
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.converter.toDomain
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.port.input.CreateClientUseCase
import com.google.gson.GsonBuilder
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RestController

@RestController
class ClientController(private val createClientUseCase: CreateClientUseCase) : ClientApi {

    private val logger = LoggerFactory.getLogger(ClientController::class.java.name)

    override fun create(createClientRequest: CreateClientRequest) {
        logger.info("Request received to create a new client: [{}]...", createClientRequest)
        val response = createClientUseCase.execute(createClientRequest.toDomain())
        logger.info("Client created: [{}].", response)
    }
}