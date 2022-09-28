package br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.port.input

import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.domain.Client
import java.util.*

interface FindClientUseCase {

    fun execute(clientId: UUID): Client

}