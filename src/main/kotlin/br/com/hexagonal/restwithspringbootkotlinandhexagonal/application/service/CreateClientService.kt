package br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.service

import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.domain.Client
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.port.input.CreateClientUseCase
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.port.output.CachePort
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.port.output.ClientAddressPort
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.port.output.ClientRepositoryPort
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.Duration

@Service
class CreateClientService(
    private val clientRepositoryPort: ClientRepositoryPort,
    private val clientAddressPort: ClientAddressPort,
    private val cachePort: CachePort,
    @Value("\${cache.ttl.inDays:10}")
    private val cacheInDays: Long
) : CreateClientUseCase {

    private val logger = LoggerFactory.getLogger(CreateClientService::class.java.name)

    private val cacheTtl = Duration.ofDays(cacheInDays)

    override fun execute(client: Client): Client {
        logger.info("Starting service to create a client.")

        client.address!!.parallelStream().forEach { clientAddress ->
            val address = cachePort.get(clientAddress.zipCode!!)
                .orElseGet {
                    clientAddressPort.getAddressClient(clientAddress.zipCode)
                        .also { address ->
                            cachePort.put(key = clientAddress.zipCode, value = address, expiration = cacheTtl)
                        }
                }
            clientAddress.setAddress(address = address, clientId = client.id)
        }
        return client.save(clientRepositoryPort).also {
            logger.info("Done service to create a client.")
        }
    }

}