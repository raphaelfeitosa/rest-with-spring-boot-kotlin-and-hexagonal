package br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.port.input

import java.util.*

interface DeleteClientUseCase {

    fun execute(clientId: UUID)

}