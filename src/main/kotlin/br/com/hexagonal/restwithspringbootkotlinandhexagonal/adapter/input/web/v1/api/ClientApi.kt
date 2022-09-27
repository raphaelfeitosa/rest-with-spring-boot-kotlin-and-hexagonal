package br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.api

import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.api.request.CreateClientRequest
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.api.request.UpdateClientRequest
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.api.response.ClientResponse
import jakarta.validation.Valid
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import java.util.UUID

@RequestMapping("/v1/clients", produces = [APPLICATION_JSON_VALUE])
interface ClientApi {

    @ResponseStatus(CREATED)
    @PostMapping(consumes = [APPLICATION_JSON_VALUE])
    fun create(@Valid @RequestBody createClientRequest: CreateClientRequest): ClientResponse

    @ResponseStatus(OK)
    @PutMapping("/{clientId}", consumes = [APPLICATION_JSON_VALUE])
    fun update(
        @PathVariable(required = true) clientId: UUID,
        @Valid @RequestBody updateClientRequest: UpdateClientRequest
    ): ClientResponse

}