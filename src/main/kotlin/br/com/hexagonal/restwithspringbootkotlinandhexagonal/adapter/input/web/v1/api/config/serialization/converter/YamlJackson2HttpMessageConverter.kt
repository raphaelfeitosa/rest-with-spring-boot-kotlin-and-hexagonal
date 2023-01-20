package br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.api.config.serialization.converter

import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper
import org.springframework.http.MediaType
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter

class YamlJackson2HttpMessageConverter : AbstractJackson2HttpMessageConverter(
    YAMLMapper().setSerializationInclusion(NON_NULL),
    MediaType.parseMediaType("application/x-yaml")
)