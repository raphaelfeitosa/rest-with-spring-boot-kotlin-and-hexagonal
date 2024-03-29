package br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.api

import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.api.request.CreateClientRequest
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.api.request.UpdateClientRequest
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.api.response.ClientResponse
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.api.utils.MediaType.APPLICATION_JSON
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.api.utils.MediaType.APPLICATION_XML
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.api.utils.MediaType.APPLICATION_YAML
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import org.springframework.http.HttpStatus.*
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("/v1/clients", produces = [APPLICATION_JSON_VALUE])
interface ClientApi {

    @ResponseStatus(CREATED)
    @PostMapping(
        consumes = [APPLICATION_JSON, APPLICATION_XML, APPLICATION_YAML],
        produces = [APPLICATION_JSON, APPLICATION_XML, APPLICATION_YAML]
    )
    fun create(@Valid @RequestBody createClientRequest: CreateClientRequest): ClientResponse

    @ResponseStatus(OK)
    @PutMapping(
        "/{clientId}",
        consumes = [APPLICATION_JSON, APPLICATION_XML, APPLICATION_YAML],
        produces = [APPLICATION_JSON, APPLICATION_XML, APPLICATION_YAML]
    )
    fun update(
        @PathVariable(required = true) @NotBlank clientId: UUID,
        @Valid @RequestBody updateClientRequest: UpdateClientRequest,
    ): ClientResponse

    @ResponseStatus(OK)
    @GetMapping(
        "/{clientId}",
        produces = [APPLICATION_JSON, APPLICATION_XML, APPLICATION_YAML]
    )
    fun findById(
        @PathVariable(required = true) @NotBlank clientId: UUID,
    ): ClientResponse

    @ResponseStatus(OK)
    @GetMapping(produces = [APPLICATION_JSON, APPLICATION_XML, APPLICATION_YAML])
    fun findAll(): List<ClientResponse>

    @ResponseStatus(NO_CONTENT)
    @DeleteMapping(
        "/{clientId}",
        produces = [APPLICATION_JSON, APPLICATION_XML, APPLICATION_YAML]
    )
    fun delete(@PathVariable(required = true) @NotBlank clientId: UUID)
}