package br.com.hexagonal.restwithspringbootkotlinandhexagonal

import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.cloud.openfeign.FeignAutoConfiguration

@ConfigurationPropertiesScan
@SpringBootApplication
@EnableFeignClients
@ImportAutoConfiguration(FeignAutoConfiguration::class)
class RestWithSpringBootKotlinAndHexagonalApplication

fun main(args: Array<String>) {
	runApplication<RestWithSpringBootKotlinAndHexagonalApplication>(*args)
}
