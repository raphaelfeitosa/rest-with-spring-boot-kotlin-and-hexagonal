package br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.service

import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.port.input.DeleteClientUseCase
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.port.output.ClientRepositoryPort
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*

@Service
class UpdateClientService(
    private val clientRepositoryPort: ClientRepositoryPort,
) : DeleteClientUseCase {

    companion object {
        private val logger = LoggerFactory.getLogger(UpdateClientService::class.java.name)
    }

    override fun execute(clientId: UUID) {
        logger.info("Starting service to delete a client with clientId: [{}].", clientId)
        return clientRepositoryPort.delete(clientId).also {
            logger.info("Done service to delete a client with clientId: [{}].", clientId)
        }
    }
}