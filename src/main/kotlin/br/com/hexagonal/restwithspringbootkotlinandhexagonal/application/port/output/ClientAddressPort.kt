package br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.port.output

import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.domain.Address

interface ClientAddressPort {

    fun getAddressClient(zipCode: String): Address

}