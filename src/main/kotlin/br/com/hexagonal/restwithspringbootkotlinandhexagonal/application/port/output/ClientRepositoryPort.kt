package br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.port.output

import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.domain.Client

interface ClientRepositoryPort {

    fun save(client: Client)

}