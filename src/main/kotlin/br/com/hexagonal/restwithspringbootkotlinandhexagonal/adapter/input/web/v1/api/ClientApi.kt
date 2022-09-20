package br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.api

import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.api.request.CreateClientRequest
import jakarta.validation.Valid
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus

@RequestMapping("/v1/clients", produces = [APPLICATION_JSON_VALUE])
interface ClientApi {

    @ResponseStatus(CREATED)
    @PostMapping(consumes = [APPLICATION_JSON_VALUE])
    fun create(@Valid @RequestBody createClientRequest: CreateClientRequest)

}