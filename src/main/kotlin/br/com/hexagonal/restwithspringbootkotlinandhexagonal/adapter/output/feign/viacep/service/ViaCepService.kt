package br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.output.feign.viacep.service

import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.controller.ClientController
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.output.feign.viacep.client.ViaCep
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.output.feign.viacep.converter.toDomain
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.domain.Address
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.port.output.ClientAddressPort
import com.google.gson.GsonBuilder
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ViaCepService(
    private val viaCep: ViaCep
) : ClientAddressPort {

    private val logger = LoggerFactory.getLogger(ClientController::class.java.name)

    override fun getAddressClient(zipCode: String): Address {
        logger.info("Starting address search by zip code: [{}].", zipCode)

        try {
            val response = viaCep.findAddressByZipCode(zipCode).toDomain()
            logger.info("Done address search. Address: [{}]", response)
            return response
        } catch (exception: Exception) {
            logger.error("Error searching address by zip code: [{}]", zipCode)
            throw exception
        }
    }
}