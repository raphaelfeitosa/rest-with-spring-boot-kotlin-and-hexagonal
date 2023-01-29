package br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.output.feign.viacep.service

import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.output.feign.viacep.client.ViaCep
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.output.feign.viacep.converter.toDomain
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.domain.Address
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.domain.errors.DomainErrors.INVALID_CEP_INTEGRATION
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.domain.exceptions.ViaCepPortException
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.port.output.ClientAddressPort
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ViaCepService(
    private val viaCep: ViaCep
) : ClientAddressPort {

    private val logger = LoggerFactory.getLogger(ViaCepService::class.java.name)

    override fun getAddressClient(zipCode: String): Address {
        logger.info("Starting address search by zip code: [{}].", zipCode)

        try {
            return viaCep.findAddressByZipCode(zipCode)
                .toDomain()
                .also {
                    logger.info("Done address search. Address: [{}]", it)
                }
        } catch (exception: Exception) {
            logger.error("Error searching address by zip code: [{}]", zipCode)
            throw ViaCepPortException(INVALID_CEP_INTEGRATION)
        }
    }
}