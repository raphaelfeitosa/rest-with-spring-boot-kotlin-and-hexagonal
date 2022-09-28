package br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.api

import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.api.request.CreateClientRequest
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.api.request.UpdateClientRequest
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.api.response.ClientResponse
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import org.springframework.http.HttpStatus.*
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("/v1/clients", produces = [APPLICATION_JSON_VALUE])
interface ClientApi {

    @ResponseStatus(CREATED)
    @PostMapping(consumes = [APPLICATION_JSON_VALUE])
    fun create(@Valid @RequestBody createClientRequest: CreateClientRequest): ClientResponse

    @ResponseStatus(OK)
    @PutMapping("/{clientId}", consumes = [APPLICATION_JSON_VALUE])
    fun update(
        @PathVariable(required = true) @NotBlank clientId: UUID,
        @Valid @RequestBody updateClientRequest: UpdateClientRequest,
    ): ClientResponse

    @ResponseStatus(OK)
    @GetMapping("/{clientId}", consumes = [APPLICATION_JSON_VALUE])
    fun findById(
        @PathVariable(required = true) @NotBlank clientId: UUID,
    ): ClientResponse

    @ResponseStatus(OK)
    @GetMapping(consumes = [APPLICATION_JSON_VALUE])
    fun findAll(): List<ClientResponse>

    @ResponseStatus(NO_CONTENT)
    @DeleteMapping("/{clientId}", consumes = [APPLICATION_JSON_VALUE])
    fun delete(@PathVariable(required = true) @NotBlank clientId: UUID)
}