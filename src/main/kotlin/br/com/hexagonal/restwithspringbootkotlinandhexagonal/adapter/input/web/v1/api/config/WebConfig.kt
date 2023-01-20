package br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.api.config

import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.api.config.serialization.converter.YamlJackson2HttpMessageConverter
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.http.MediaType.APPLICATION_XML
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {

    companion object {
        private val APPLICATION_YAML = MediaType.valueOf("application/x-yaml")
    }

    override fun extendMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
        converters.add(YamlJackson2HttpMessageConverter())
    }

    override fun configureContentNegotiation(configurer: ContentNegotiationConfigurer) {
        configurer
            .favorParameter(false)
            .ignoreAcceptHeader(false)
            .useRegisteredExtensionsOnly(false)
            .defaultContentType(APPLICATION_JSON)
            .mediaType("json", APPLICATION_JSON)
            .mediaType("xml", APPLICATION_XML)
            .mediaType("x-yaml", APPLICATION_YAML)
    }

}