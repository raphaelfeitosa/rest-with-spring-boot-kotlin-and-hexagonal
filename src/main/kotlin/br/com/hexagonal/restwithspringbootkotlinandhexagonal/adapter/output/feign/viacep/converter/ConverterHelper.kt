package br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.output.feign.viacep.converter

import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.output.feign.viacep.response.ViaCepResponse
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.domain.Address


fun ViaCepResponse.toDomain() = Address(
    street = this.street,
    district = this.district,
    city = this.city,
    state = this.state
)