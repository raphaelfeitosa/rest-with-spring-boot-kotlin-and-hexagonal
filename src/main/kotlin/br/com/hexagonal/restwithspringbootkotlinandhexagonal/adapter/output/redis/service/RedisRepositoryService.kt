package br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.output.redis.service

import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.output.redis.repository.RedisRepository
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.domain.Address
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.port.output.CachePort
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.Duration
import java.util.Optional

@Service
class RedisRepositoryService(
    private val redisRepository: RedisRepository
) : CachePort {

    companion object {
        private val logger = LoggerFactory.getLogger(RedisRepositoryService::class.java.name)
    }

    override fun put(key: String, value: Any, expiration: Duration) {
        logger.info(
            "Starting process to put in cache value: [{}] with key: [{}] and expiration in [{}] days.",
            value,
            key,
            expiration
        )
        runCatching {
            redisRepository.put(key = key, value = value, expiration = expiration)
            logger.info(
                "Done process to put in cache value: [{}] with key: [{}] and expiration in [{}] days.",
                value,
                key,
                expiration
            )
        }.getOrElse {
            logger.error(
                "Application was not able to put data from cache with key: [{}] and exception: [{}].",
                key,
                it.cause
            )
        }
    }

    override fun get(key: String): Optional<Address> {
        logger.info("Starting process to get address in cache with key: [{}].", key)
        val cacheResponse = runCatching { redisRepository.get(key = key) as Address? }

        return cacheResponse.fold(
            onSuccess = { address ->
                logger.info(
                    "Done process to get address: [{}] in cache with key: [{}].",
                    address,
                    key
                )
                Optional.ofNullable(address)
            },
            onFailure = { exception ->
                logger.error(
                    "Application was not able to put data from cache with key: [{}] and exception: [{}].",
                    key,
                    exception.cause
                )
                Optional.empty()
            }
        )
    }

}