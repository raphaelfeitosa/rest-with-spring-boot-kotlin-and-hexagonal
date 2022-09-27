package br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.port.input

import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.domain.Client

interface UpdateClientUseCase {

    fun execute(client: Client): Client

}