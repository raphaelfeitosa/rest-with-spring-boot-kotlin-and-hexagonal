package br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.output.redis.repository

import java.time.Duration

interface RedisRepositoryPort {

    fun put(key: String, value: Any, expiration: Duration)
    fun get (key: String): Any?

}