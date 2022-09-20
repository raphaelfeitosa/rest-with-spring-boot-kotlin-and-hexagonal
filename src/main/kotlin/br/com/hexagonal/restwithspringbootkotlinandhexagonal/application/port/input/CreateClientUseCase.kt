package br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.port.input

import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.domain.Client

interface CreateClientUseCase {

    fun execute(client: Client)

}