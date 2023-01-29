package br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.output.feign.viacep.client

import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.output.feign.viacep.response.ViaCepResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(
    name = "via-cep",
    url = "\${api-via-cep.url}"
)
interface ViaCepClient {

    @GetMapping("/ws/{zipCode}/json", consumes = [APPLICATION_JSON_VALUE])
    fun findAddressByZipCode(
        @PathVariable("zipCode", required = true) zipCode: String
    ): ViaCepResponse
}