package br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.service

import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.domain.Client
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.port.input.FindClientUseCase
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.port.output.ClientRepositoryPort
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*

@Service
class FindClientService(
    private val clientRepositoryPort: ClientRepositoryPort,
) : FindClientUseCase {

    private val logger = LoggerFactory.getLogger(FindClientUseCase::class.java.name)
    override fun execute(): List<Client> {
        logger.info("Starting service to find all clients.")
        return clientRepositoryPort.findAllAndActiveTrue().also {
            logger.info("Done service to find all clients.")
        }
    }

    override fun execute(clientId: UUID): Client {
        logger.info("Starting service to find a client by clientId: [{}].", clientId)
        return clientRepositoryPort.findByIdAndActiveTrue(clientId).also {
            logger.info("Done service to find a client: [{}].", it)
        }
    }
}