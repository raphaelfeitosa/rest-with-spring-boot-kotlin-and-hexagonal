package br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.output.redis.repository

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.data.redis.RedisConnectionFailureException
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository
import java.time.Duration

@Repository
class RedisRepository(
    private val redisTemplate: RedisTemplate<String, Any>
) : RedisRepositoryPort {

    private val logger = LoggerFactory.getLogger(RedisRepository::class.java.name)

    override fun put(key: String, value: Any, expiration: Duration) {
        try {
            redisTemplate.opsForValue().set(key, value, expiration)
        } catch (exception: RedisConnectionFailureException) {
            logger.error(
                "Timeout with redis to put value: [{}] with key: [{}].",
                value,
                key,
                exception
            )
        }
    }

    override fun get(key: String): Any? {
        return try {
            redisTemplate.opsForValue().get(key)
        } catch (exception: RedisConnectionFailureException) {
            logger.error(
                "Timeout with Redis to get key: [{}].",
                key,
                exception
            )
        }
    }

}