package br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.port.output

import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.domain.Address
import java.time.Duration
import java.util.Optional

interface CachePort {

    fun put(key: String, value: Any, expiration: Duration)
    fun get (key: String): Optional<Address>

}