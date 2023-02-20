package br.com.hexagonal.restwithspringbootkotlinandhexagonal

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@ConfigurationPropertiesScan
@SpringBootApplication
@EnableFeignClients
class RestWithSpringBootKotlinAndHexagonalApplication

fun main(args: Array<String>) {
	runApplication<RestWithSpringBootKotlinAndHexagonalApplication>(*args)
}
