package br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.service

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

    private val logger = LoggerFactory.getLogger(CreateClientService::class.java.name)

    override fun execute(client: Client): Client {
        logger.info("Starting service to create a client.")

        client.address!!.parallelStream().forEach { clientAddress ->
           val address = clientAddressPort.getAddressClient(clientAddress.zipCode!!)

            clientAddress.setAddress(address = address, clientId = client.id)
        }
        return client.save(clientRepositoryPort).also {
            logger.info("Done service to create a client.")
        }
    }

}