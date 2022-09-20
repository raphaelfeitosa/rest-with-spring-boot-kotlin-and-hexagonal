package br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.service

import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.controller.ClientController
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.domain.Client
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.port.input.CreateClientUseCase
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.port.output.ClientAddressPort
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.port.output.ClientRepositoryPort
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class CreateClientService(
    private val clientRepositoryPort: ClientRepositoryPort,
    private val clientAddressPort: ClientAddressPort
) : CreateClientUseCase {

    private val logger = LoggerFactory.getLogger(ClientController::class.java.name)

    override fun execute(client: Client) {
        logger.info("Starting service to create a client.")
        clientAddressPort.getAddressClient(client.address.zipCode!!).also {
           client.address.street = it.street
            client.address.district = it.district
            client.address.state = it.state
            client.address.city = it.city
        }
        client.save(clientRepositoryPort)
        logger.info("Done service to create a client.")
    }
}